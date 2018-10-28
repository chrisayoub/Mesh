# http://www.lejos.org/nxt/nxj/tutorial/Preliminaries/GettingStartedLinux.htm

sudo apt install libusb-dev ant

echo 'PATH="/opt/lejos/bin:$PATH"' >> ~/.profile
echo 'NXJ_HOME="/opt/lejos"' >>  ~/.profile

mkdir /opt/lejos
tar -zxvf lejos.tar.gz
sudo mv -v ./leJOS_NXJ_0.9.1beta-3/ /opt/lejos

( cd /opt/lejos/build && ant )
