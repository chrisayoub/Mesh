# https://www.raspberrypi.org/documentation/configuration/wireless/access-point.md#internet-sharing
# MUST RUN THIS SCRIPT AS SUDO

apt-get install hostapd bridge-utils
systemctl stop hostapd

echo dhcpcd.conf >> /etc/dhcpcd.conf

brctl addbr br0
brctl addif br0 eth0

echo interfaces >> /etc/network/interfaces

echo hostapd.conf > /etc/hostapd/hostapd.conf

echo 'DAEMON_CONF="/etc/hostapd/hostapd.conf"' >> /etc/default/hostapd

# reboot

