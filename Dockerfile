FROM openjdk:8-jdk-alpine

WORKDIR /app

COPY ./build/libs/* ./app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]