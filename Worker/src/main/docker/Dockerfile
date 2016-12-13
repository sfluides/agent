FROM szagoret/alpine-3.3-jre8
VOLUME /tmp
ADD worker.jar worker.jar
RUN sh -c 'touch /worker.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/worker.jar"]