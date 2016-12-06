FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD auth.jar auth.jar
RUN sh -c 'touch /auth.jar'
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","-jar","/auth.jar"]