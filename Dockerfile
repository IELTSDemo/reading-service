FROM openjdk:21-jdk
WORKDIR /app
COPY target/reading-service-0.0.2-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
