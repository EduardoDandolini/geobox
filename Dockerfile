FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY target/geobox-0.0.1-SNAPSHOT.jar /app/geobox-0.0.1-SNAPSHOT.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/geobox-0.0.1-SNAPSHOT.jar"]