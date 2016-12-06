#!/bin/bash

SERVICEMIX_PATH=/home/vmuser/data/apache-servicemix-7.0.0.M2
ZOO_PATH=/home/vmuser/data/zookeeper
SLING_PATH=/home/vmuser/data/Sling
APPPATH="/home/vmuser/work/last/Agent"
SCRIPTHOME=/home/vmuser/data


function start_mix {
 ### start service mix ###
 ### servixmix EXPOSES ports: 1099 61616 8181 8101 40887 40327 44444 ###
 cd $SERVICEMIX_PATH
 rm -r $SERVICEMIX_PATH/instance.properties
 chmod +x $SERVICEMIX_PATH/bin/*
 $SERVICEMIX_PATH/bin/start

 ### start zookeeper ###
 ### zookeper EXPOSES ports: 2181 38265 ###
 chmod +x $ZOO_PATH/bin/zkServer.sh
 cd $ZOO_PATH
 $ZOO_PATH/bin/zkServer.sh start
 ### check zookeeper status ###
 ### /home/vmuser/work/withDocker/fulldockerized/zoo/zookeeper/bin/zkServer.sh status
 ### ZooKeeper JMX enabled by default ###
 ### Using config: zookeeper/bin/../conf/zoo.cfg ###
 ### Mode: standalone ###

 cd $SLING_PATH
 screen -d -m -S "Sling" java -jar *.jar
}


######  main  ######
start_mix

echo "Checking status: ..."
echo
echo List all detached screens:
echo
screen -ls
echo "To attach to a screen:  screen -r <idscreen>" 
echo "To detach the screen again: CTRL+A then D "
echo
$SERVICEMIX_PATH/bin/start
$ZOO_PATH/bin/zkServer.sh status
ps -ef | grep sling
# systemctl status mongodb 
# systemctl status elasticsearch
# systemctl status kibana 
######  endmain  ######







