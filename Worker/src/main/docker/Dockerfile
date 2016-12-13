FROM szagoret/alpine-jdk8
VOLUME /resources
ADD worker.jar worker.jar
RUN sh -c 'touch /worker.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/worker.jar"]