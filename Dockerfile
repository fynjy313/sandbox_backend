# FROM adoptopenjdk/openjdk11:alpine-jre
FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/sandbox_backend-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]