# https://www.raspberrypi.org/documentation/configuration/wireless/access-point.md#internet-sharing

sudo apt-get install hostapd bridge-utils
sudo systemctl stop hostapd

sudo echo dhcpcd.conf >> /etc/dhcpcd.conf

sudo brctl addbr br0
sudo brctl addif br0 eth0

sudo echo interfaces >> /etc/network/interfaces

sudo echo hostapd.conf > /etc/hostapd/hostapd.conf

sudo echo 'DAEMON_CONF="/etc/hostapd/hostapd.conf"' >> /etc/default/hostapd

sudo reboot

