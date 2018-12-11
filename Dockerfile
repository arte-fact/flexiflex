FROM openjdk:8-jdk-alpine

COPY target/flexiflex-0.0.1.jar app.jar

EXPOSE 8080

CMD java -jar app.jar
