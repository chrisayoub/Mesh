# https://www.acmesystems.it/python_http

#!/usr/bin/python
from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import os
import re
import json
import threading
import time

PORT_NUMBER = 8080
CMD = 'iw wlan1 station dump'

#This class will handles any incoming request from
#the browser 

# Station xx:xx:xx:xx:xx:xx (on wlan1)
# 	signal:  	-38 dBm

class myHandler(BaseHTTPRequestHandler):
	
	#Handler for the GET requests
	def do_GET(self):
		self.send_response(200)
		self.end_headers()

		if self.path == '/start':
			self.startPing()
			return
		elif self.path == '/stop':
			self.stopPing()
			return

		# Send the html message
		# This is for returning JSON for device signal strength
		data = str(os.popen(CMD).read()).strip()
		if data == '':
			return
		lines = data.split('\n')

		result = {}

		for i in range(0, len(lines), 2):
			dev = lines[i]
			signal = lines[i + 1]
			devMac = dev.split(' ')[1]
			signalVal = re.compile("[ \t]").split(signal)[4]
			signalVal = int(signalVal)
			result[devMac] = signalVal

		self.wfile.write(json.dumps(result))
		return

	doPing = True

	def startPing(self):
		self.doPing = True
		thr = threading.Thread(target=self.beginPing)
		# thr.setDaemon(True)
		thr.start()

	def beginPing(self):
		# First, get IP of client (assume only one client)
		IP_CMD = 'cat /var/lib/misc/dnsmasq.leases'
		data = str(os.popen(IP_CMD).read()).strip()
		if data == '':
			return
		print(data)
		ip = data.split(' ')[2]
		print (ip)

		PING_CMD = 'ping -c 1 ' + ip
		print(PING_CMD)
		# It takes about 2 ms to do a local ping
		# Do this at 50 ms rate, lower than 100 ms RTT from central
		TIME = 2
		TGT = 50
		RATE = TGT / TIME
		while self.doPing: # This value should be changed, hopefully
			os.system(PING_CMD)
			time.sleep(RATE / 1000.0) # Milliseconds
		print('Stopped!')

	def stopPing(self):
		print('stop ping')
		self.doPing = False


try:
	#Create a web server and define the handler to manage the
	#incoming request
	server = HTTPServer(('', PORT_NUMBER), myHandler)
	print 'Started httpserver on port ' , PORT_NUMBER
	
	#Wait forever for incoming htto requests
	server.serve_forever()

except KeyboardInterrupt:
	print '^C received, shutting down the web server'
	server.socket.close()
	os._exit(0)
