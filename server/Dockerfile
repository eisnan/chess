FROM openjdk:20-jdk-slim

WORKDIR /app
COPY ./target/server.jar /app

EXPOSE 8080

CMD ["java", "-jar", "server.jar"]