# https://somesquares.org/blog/2017/10/Raspberry-Pi-router/
# MUST RUN THIS SCRIPT AS SUDO

# For drivers for WLAN device:
# https://www.raspberrypi.org/forums/viewtopic.php?t=219751
# https://github.com/lwfinger/rtl8188eu

apt-get install hostapd
cat hostapd.conf > /etc/hostapd/hostapd.conf
echo 'DAEMON_CONF="/etc/hostapd/hostapd.conf"' >> /etc/default/hostapd

apt-get install dnsmasq
cat dhcpcd.conf >> /etc/dhcpcd.conf
cat dnsmasq.conf >> /etc/dnsmasq.conf

echo 'net.ipv4.ip_forward=1' >> /etc/sysctl.conf

# wlan0 is source, wlan1 is AP
iptables -t nat -A  POSTROUTING -o wlan0 -j MASQUERADE
# Don't need these...
# iptables -A FORWARD -i wlan0 -o wlan1 -m state --state RELATED,ESTABLISHED -j ACCEPT
# iptables -A FORWARD -i wlan1 -o wlan0 -j ACCEPT

apt-get install iptables-persistent

