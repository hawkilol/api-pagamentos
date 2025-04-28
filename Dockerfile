FROM maven:3.9.9-eclipse-temurin-17-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk
COPY --from=build /target/api-pagamentos-0.0.1-SNAPSHOT.jar api-pagamentos.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","api-pagamentos.jar"]