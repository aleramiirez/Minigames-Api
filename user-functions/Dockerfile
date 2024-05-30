FROM openjdk:17-jdk-alpine
COPY target/user-functions-0.0.1-SNAPSHOT.jar user-functions.jar
ENTRYPOINT ["java", "-jar", "user-functions.jar"]