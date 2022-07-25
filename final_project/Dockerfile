FROM alpine:3.15
RUN apk add openjdk11
COPY target/*.jar /app.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/app.jar"]