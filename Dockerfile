FROM maven:3.9.4-eclipse-temurin-17-alpine as build
COPY . .
RUN mvn clean package -DskipTests
COPY --from=build ./target/spring-jasper-0.0.1-SNAPSHOT.jar spring-jasper.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-jasper.jar"]