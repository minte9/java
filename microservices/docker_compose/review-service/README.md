## Review Service (Docker)

From the microservices' directory:

### Add a Dockerfile
 
    # review-service/Dockerfile

    FROM eclipse-temurin:17-jre-alpine
    WORKDIR /app
    ARG JAR_FILE=build/libs/*.jar
    COPY ${JAR_FILE} app.jar
    EXPOSE 7002
    ENTRYPOINT ["java", "-jar", "/app/app.jar"]

### Build the JAR (Outside Docker)

    cd review-service
    ./gradlew clean build
    
    build/lib/review-service-1.0.0.jar

### Build the Docker image

    docker build -t review-service:1.0 .
    docker images

    REPOSITORY               TAG          IMAGE ID       CREATED          SIZE
    review-service           1.0          e15379e32829   4 seconds ago    207MB
    product-service          1.0          f75e4ed58dd9   11 minutes ago   207MB

### Run the Container

    docker run -d -p 7002:7002 --name review-service review-service:1.0

### Test

    curl http://localhost:7002/review?productId=1

    [{"productId":1,"reviewId":1,"author":"Alice","subject":"Great!","content":"Really liked this product"},
     {"productId":1,"reviewId":2,"author":"Bob","subject":"Good","content":"Does the job well"}]
