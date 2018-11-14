# https://www.acmesystems.it/python_http

#!/usr/bin/python
from BaseHTTPServer import BaseHTTPRequestHandler, HTTPServer
import os
import re
import json

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
		# Send the html message
		data = str(os.popen(CMD).read()).strip()
		lines = data.split('\n')

		result = {}

		for i in range(0, len(lines), 2):
			dev = lines[i]
			signal = lines[i + 1]
			devMac = dev.split(' ')[1]
			signalVal = re.compile("[ \t]{2,}").split(signal)[1]
			result[devMac] = signalVal

		self.wfile.write(json.dumps(result))
		return

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