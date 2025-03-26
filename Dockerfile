FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

COPY pom.xml ./
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:21-jdk-slim

WORKDIR /app

RUN addgroup --system inditex_group && adduser --system --group inditex_user
USER inditex_user

COPY --from=build /app/target/inditex-0.0.1-SNAPSHOT.jar /app/inditex-0.0.1-SNAPSHOT.jar

ENV DB_USERNAME=root \
    DB_PASSWORD=root \
    DB_CONNECTION_URL=jdbc:mysql://mysql:3306/inditex

EXPOSE 3000

HEALTHCHECK --interval=30s --timeout=10s --retries=3 --start-period=30s \
    CMD wget --quiet --spider http://localhost:3000/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "/app/inditex-0.0.1-SNAPSHOT.jar"]