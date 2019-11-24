#! /bin/sh
#
SOFile="usr/lib/liblpsolve55.so"
if [ ! -f $SOFile ]; then
sudo cp ~/.netlogo/6.1/extensions/lpsolver/liblpsolve55.so /usr/lib/
fi

