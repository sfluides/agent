#!/usr/bin/env bash
# build image: mvn package docker:build -DskipTests
# inspect container properties: docker inspect [container-id]
mvn package docker:build -DskipTests
docker run  -e port=8088  -e zooHost=10.185.5.51 -p 8088:8088 -t smartfluides/agent.auth
