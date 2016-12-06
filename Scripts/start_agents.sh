#!/bin/bash

SERVICEMIX_PATH=/home/vmuser/data/apache-servicemix-7.0.0.M2
ZOO_PATH=/home/vmuser/data/zookeeper
SLING_PATH=/home/vmuser/data/Sling
APPPATH="/home/vmuser/work/last/Agent"
SCRIPTHOME=/home/vmuser/data


function build_rerun_auth {
 ### start the authentication service
 cd $APPPATH/Auth
 screen -S "authentication" -X quit
 screen -d -m -S "authentication" mvn spring-boot:run -Dport=8088 -DzooHost=localhost
}


function build_rerun_dispatcher {
 ### start the dispatcher service
 cd $APPPATH/Dispatcher
 screen -S "dispatcher" -X quit
 screen -d -m -S "dispatcher" mvn spring-boot:run -Dport=8081 -DzooHost=localhost -DamqHost=localhost -DmongoHost=localhost
}


function stop_workers {
 for WORKER in `screen -ls | grep worker | awk '{print $1}'`;
 do 
  screen -S $WORKER -X quit
 done
}


function build_rerun_workers {
 ### start workers services
 stop_workers
 cd $APPPATH/Scripts
 . start_workers.sh listsparams.txt
 ## start one worker
 ## screen -d -m -S "worker" mvn spring-boot:run -D spring.application.name=importer -Dworker.id=Worker8085
}



######  main  ######
build_rerun_auth
build_rerun_dispatcher
build_rerun_workers

echo
echo List all detached screens:
echo
screen -ls
echo "To attach to a screen:  screen -r <idscreen>"
echo "To detach the screen again: CTRL+A then D "
######  endmain  ######






