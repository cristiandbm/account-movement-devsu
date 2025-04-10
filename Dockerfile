FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

COPY build/libs/account-movement-devsu-0.0.1-SNAPSHOT.jar account-movement.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "account-movement.jar"]