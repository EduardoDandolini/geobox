FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/geobox-0.0.1-SNAPSHOT.jar /app/geobox-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/geobox-0.0.1-SNAPSHOT.jar"]