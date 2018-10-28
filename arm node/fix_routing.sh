# https://raspberrypi.stackexchange.com/questions/71348/cannot-forward-from-wlan-interface-to-another-wlan-interface

sudo route del -net 0.0.0.0 dev wlan1
