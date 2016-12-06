#!/usr/bin/env bash
# build image: mvn package docker:build -DskipTests
# inspect container properties: docker inspect [container-id]
mvn package docker:build -DskipTests
docker run  -e workerType=importer -e port=8081 -e authHost=10.185.5.51 -e zooHost=10.185.5.51  -e amqHost=10.185.5.51 -p 8081:8081 -t smartfluides/agent.dispatcher
