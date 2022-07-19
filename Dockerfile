
FROM openjdk:17

WORKDIR /app

COPY /target/oauth-service.jar /app/oauth-service.jar

EXPOSE 10101

ENTRYPOINT ["java", "-jar", "oauth-service.jar"]

