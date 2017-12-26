FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD common-service-0.0.1-SNAPSHOT.jar app.jar
#RUN bash -c 'touch /app.jar'
EXPOSE 8030
#ENTRYPOINT ["java","-jar","/workspace/eureka-1.0-SNAPSHOT.jar"]
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]