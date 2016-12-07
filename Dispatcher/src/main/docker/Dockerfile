#TODO change base image
FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD dispatcher.jar dispatcher.jar
RUN sh -c 'touch /dispatcher.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/dispatcher.jar"]