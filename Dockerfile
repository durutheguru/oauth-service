
FROM openjdk:17-slim

WORKDIR /app

COPY /target/oauth-service.jar /app/oauth-service.jar

EXPOSE 10101

ENTRYPOINT ["java", "-jar", "oauth-service.jar"]

