#Stage 1
FROM maven:3.9.6-eclipse-temurin-17 as builder

WORKDIR /app

#Copy dependencies
COPY . .

RUN mvn clean package -DskipTests

#Stage 2
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]