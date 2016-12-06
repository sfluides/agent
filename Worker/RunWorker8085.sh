#!/usr/bin/env bash
# build image: mvn package docker:build -DskipTests
# inspect container properties: docker inspect [container-id]

mvn package docker:build -DskipTests
docker run -v /home/vmuser/data/apache-servicemix-7.0.0.M2/camel/import:/opt/import -e workerType=importer -e port=8085 -e workerId=Worker8085 -e authHost=10.185.5.51 -e zooHost=10.185.5.51 -e mongoHost=10.185.5.51 -e amqHost=10.185.5.51 -e importDir=/opt/import -e elasticHost=10.185.5.51 -e couchHost=10.185.5.51  -e slingHost=10.185.5.51 -p 8085:8085 -t smartfluides/agent.worker
