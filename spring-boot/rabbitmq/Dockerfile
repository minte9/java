# ---------- Build stage ----------
FROM maven:3.9.8-eclipse-temurin-17 AS build

WORKDIR /app

# Copy project files
COPY pom.xml .
COPY src ./src

# Build jar, skipping tests for simplicity in demo
RUN mvn clean package -DskipTests

# ---------- Run stage ----------
FROM eclipse-temurin:17-jdk

WORKDIR /app

# Copy built jar from previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose Spring Boot port
EXPOSE 9090

# Start the application
ENTRYPOINT ["java", "-jar", "app.jar"]