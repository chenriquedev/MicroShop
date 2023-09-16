FROM openjdk:17-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/shopping-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
LABEL  authors="krlos"

ENTRYPOINT ["java", "-jar", "/app.jar"]