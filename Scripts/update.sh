#!/bin/bash

SERVICEMIX_PATH=/home/vmuser/data/apache-servicemix-7.0.0.M2
ZOO_PATH=/home/vmuser/data/zookeeper
SLING_PATH=/home/vmuser/data/Sling
APPPATH="/home/vmuser/work/last/Agent"
SCRIPTHOME=/home/vmuser/data

function stop_agents {
 for AGENT in `screen -ls | grep 'worker\|auth\|disp' | awk '{print $1}'`;
 do 
  screen -S $AGENT -X quit
 done
}


function get_sources {
 cd $APPPATH
 svn status --show-updates
 svn update
 svn status --show-updates
 mvn clean
 mvn package -Dmaven.test.skip=true
}


######  main  ######
stop_agents
get_sources

######  endmain  ######







