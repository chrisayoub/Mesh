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
- Program the arm movement based on the signal (work on this algorithm more)
- Create client program that will initiate the adjustment, and the client will then ping the arm node until done
- Run tests that see if the adjustment gives any noticeable benefit, try with multiple types of network activity

