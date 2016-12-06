#!/bin/bash
########################################################################
### HOW TO RUN IT FROM Agent/Worker folder                           ###
### EX1:                                                             ###
### /home/vmuser/data/startWorkers.sh 
###                                                                  ###
### EX2:                                                             ###
### /home/vmuser/data/startWorkers.sh /home/vmuser/data/listsparams.txt 
########################################################################
########################################################################
### HOW TO RUN IT FROM SAME DIRECTORY or from ANYWHERE               ###
### --- 1. UNCOMMENT and UPDATE the line WORKERS_HOME ---            ###
### --- 2. UNCOMMENT the first line fromfunctionnnnnnnnnnn myF ---   ###
###                                                                  ###
### EX1:                                                             ###
### ./startWorkers.sh 
###                                                                  ###
### EX2:                                                             ###
### ./startWorkers.sh /home/vmuser/data/listsparams.txt
########################################################################

INPUTFILE=listsparams.txt
WORKERS_HOME="/home/vmuser/work/last/Agent/Worker"
JVMXXFILE="/home/vmuser/work/last/Agent/Scripts/jvm_params.txt"
JVMXX=''



function myF {
### uncomment this to run from anywhere ### 
 cd $WORKERS_HOME
 echo Starting worker: $1 "--" $2 "--" $3
 JVMARG="-Dport="$1
 JVMARG=$JVMARG" -DworkerType="$2" -DworkerId="$3" -DzooHost=localhost -DmongoHost=localhost -DcouchHost=localhost -DslingHost=localhost -DelasticHost=localhost -DimportDir=/home/vmuser/data/apache-servicemix-7.0.0.M2/camel/import"
 echo jvmArguments "mvn spring-boot:run" $JVMXX $JVMARG
 aux=$JVMXX$JVMARG
 screen -d -m -S "worker-"$3 mvn spring-boot:run -Drun.jvmArguments="$aux"

}


function addAllJVMXX {
 params=`cat $JVMXXFILE | grep -v "#" | wc -l`

 if [ $params -ne 0 ]
 then
   while read LINE
   do
     echo $LINE
     JVMXX=$JVMXX"-XX:"$LINE" "
     #control point# echo $JVMXX
   done <<< "$(cat $JVMXXFILE | grep -v "#")"
 else
   echo "No aditional JVM args are enbaled in " $JVMXXFILE
 fi
}
 
 

 addAllJVMXX

 echo
 echo
 
 if [ $# -eq 0 ]
 then
   echo "No input file provided; default is used"
 else
   INPUTFILE=$1
 fi

 cat $INPUTFILE | while read LINE
 do
   echo 
   myF $LINE
 done

screen -ls
