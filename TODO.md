# TODO List

- ~~Make central node an AP~~, DONE
- ~~Allow central node to control NXT motor via wired or Bluetooth~~, DONE over USB
- ~~Make arm node an AP, while bridging the connection to central node over a different wireless interface~~, DONE, creates new network for devices connected over arm, proxying possible but currently not implemented
- ~~Make arm node signal metric accessible to central node over SSH or API or something `iw wlan1 station dump`~~, DONE, node can be accessed from parent network
- ~~Implement web server to access signal metrics~~, DONE, used Python to return JSON for each connected MAC
- ~~Run web server on startup~~
- ~~Static IP for both nodes~~, DONE, used router
- ~~Have code to rotate arm 90 degrees forward and backwards, signaled by USB~~
- ~~Have some way to initiate the adjustment of the arm~~, executed via GET request (Spring Boot)
- ~~Program the arm movement based on the signal~~, algorithm divides into eighths and refines as close as possible
- ~~Make persistent traffic to client during adjustment (endpoint for arm node that makes it ping the client with ICMP at set rate, will request from central node)~~ done, use /start and /stop endpoints
- ~~Mount nodes in position, including battery for arm node~~
- Testing!
	- ~~Try and conduct where low 2.4 GHz conflicting signals~~, need to use channels 1 and 6, least interference
	- ~~Update hostapd of arm node for new channel~~, enabled wireless N
	- ~~Fix NXT connection so manually do USB, use NXT rotate methods~~
	- Verify manually that the arm gets to correct position (close to client)
	- Find method to get current network throughput at the client (maybe automatically display a graph, too?)
	- Compare throughput with file read from network (not Internet), before and after adjust in worst-case to best-case
	- Show results for when change occurs DURING the network activity (look for spikes?)

