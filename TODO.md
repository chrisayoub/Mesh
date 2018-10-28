# TODO List

- ~~Make central node an AP~~, DONE
- ~~Allow central node to control NXT motor via wired or Bluetooth~~, DONE over USB
- Make arm node an AP, while bridging the connection to central node over a different wireless interface
- Make arm node signal metric accessible to central node over SSH or API or something `iw dev wlan0 station dump`
- Have code to rotate arm 90 degrees forward and backwards, signaled by USB
- Have some way to initiate the arm movement based on the signal metric
- Program the arm movement based on the signal (client needs to ping the AP)
- Automatically run the arm movement based on feedback or intervals

