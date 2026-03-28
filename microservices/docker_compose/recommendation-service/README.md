## Recommendation Service (Docker)

From the microservices' directory:

### Add a Dockerfile

    # recommendation-service/Dockerfile

    FROM eclipse-temurin:17-jre-alpine
    WORKDIR /app
    ARG JAR_FILE=build/libs/*.jar
    COPY ${JAR_FILE} app.jar
    EXPOSE 7002
    ENTRYPOINT ["java", "-jar", "/app/app.jar"]

### Build the JAR (Outside Docker)

    cd recommendation-service
    ./gradlew clean build
    
    build/lib/recommendation-service-1.0.0.jar

### Build the Docker image

    docker build -t recommendation-service:1.0 .
    docker images

    REPOSITORY               TAG       IMAGE ID       CREATED          SIZE
    recommendation-service   1.0       d07d9ae5e9aa   10 minutes ago   207MB
    review-service           1.0       e15379e32829   20 minutes ago   207MB
    product-service          1.0       f75e4ed58dd9   32 minutes ago   207MB
    
    # Remove image (if needed):
    # docker rmi recomendation-service
    # docker images prune -a

### Run the Container

    docker run -d -p 7003:7003 --name recommendation-service recommendation-service:1.0

### Test

    curl http://localhost:7003/recommendation?productId=1

    [{"productId":1,"recommendationId":1,"author":"Carol","rating":5,"content":"Higly recommended"},
     {"productId":1,"recommendationId":2,"author":"Dave","rating":4,"content":"Worth buying"}]
