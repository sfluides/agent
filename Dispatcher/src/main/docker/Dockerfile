FROM szagoret/alpine-jdk8
VOLUME /resources
ADD dispatcher.jar dispatcher.jar
RUN sh -c 'touch /dispatcher.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/dispatcher.jar"]