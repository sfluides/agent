FROM szagoret/alpine-jdk8
VOLUME /resources
ADD auth.jar auth.jar
RUN sh -c 'touch /auth.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/auth.jar"]