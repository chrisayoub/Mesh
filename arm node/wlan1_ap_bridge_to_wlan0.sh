# https://www.raspberrypi.org/documentation/configuration/wireless/access-point.md#internet-sharing
# MUST RUN THIS SCRIPT AS SUDO

apt-get install hostapd bridge-utils
systemctl stop hostapd

cat dhcpcd.conf >> /etc/dhcpcd.conf

brctl addbr br0
brctl addif br0 wlan0

cat interfaces >> /etc/network/interfaces

cat hostapd.conf > /etc/hostapd/hostapd.conf

echo 'DAEMON_CONF="/etc/hostapd/hostapd.conf"' >> /etc/default/hostapd

# reboot

