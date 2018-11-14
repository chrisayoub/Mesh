
# Launches python server on startup

path=`pwd`
dest='/etc/rc.local'

echo '#!/bin/sh -e' > ${dest}
echo "\n" >> ${dest}

echo "python ${path}/server.py &" >> ${dest}
echo 'exit 0' >> ${dest}
