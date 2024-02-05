FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar validation-service-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/validation-service-1.0.0.jar"]