# https://raspberrypi.stackexchange.com/questions/50107/how-to-reserve-wlan0-for-embeded-wifi-on-rasberry-pi-3

# Run as sudo !

cat int.rules > /etc/udev/rules.d/70-persistent-net.rules
