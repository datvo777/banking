
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/banking-0.0.1-SNAPSHOT.jar
COPY --from=build ${JAR_FILE} banking.jar
ENTRYPOINT ["java", "-jar", "/banking.jar"] 