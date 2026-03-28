## Product Service (Docker)

From the microservices' directory:  

### Add a Dockerfile

      # produce-service/Dockerfile

      FROM eclipse-temurin:17-jre-alpine
      WORKDIR /app
      ARG JAR_FILE=build/libs/*.jar
      COPY ${JAR_FILE} app.jar
      EXPOSE 7001
      ENTRYPOINT ["java", "-jar", "/app/app.jar"]

### Build the JAR (Outside Docker)  

      cd product-service
      ./gradlew clean build
      
      build/lib/product-service-1.0.0.jar

### Build the Docker image

      docker build -t product-service:1.0 .
      docker images

      REPOSITORY               TAG          IMAGE ID       CREATED         SIZE
      product-service          1.0          f75e4ed58dd9   4 seconds ago   207MB

### Run the Container

      docker run -d -p 7001:7001 --name product-service product-service:1.0

### Test

      curl http://localhost:7001/product/1

      {"productId":1,"name":"Product-1","weight":100}