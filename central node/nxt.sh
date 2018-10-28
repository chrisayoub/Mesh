# http://www.lejos.org/nxt/nxj/tutorial/Preliminaries/GettingStartedLinux.htm

sudo apt install libusb-dev

echo 'PATH="/opt/lejos/bin:$PATH"' >> ~/.profile
echo 'NXJ_HOME="/opt/lejos"' >>  ~/.profile

mkdir /opt/lejos
tar -C /opt/lejos -zxvf yourfile.tar.gz