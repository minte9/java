## Product-Composite Service (Docker)

From the microservices' directory:

### Add a Dockerfile

    # produce-composite-service/Dockerfile
    
    FROM eclipse-temurin:17-jre-alpine
    WORKDIR /app
    ARG JAR_FILE=build/libs/*.jar
    COPY ${JAR_FILE} app.jar
    EXPOSE 7001
    ENTRYPOINT ["java", "-jar", "/app/app.jar"]

### Build the JAR (Outside Docker)

    cd product-composite-service
    ./gradlew clean build

    build/lib/product-composite-service-1.0.0.jar

### Build the Docker image

    docker build -t product-composite-service:1.0 .
    docker images

    REPOSITORY                  TAG       IMAGE ID       CREATED             SIZE
    product-composite-service   1.0       847cc38f9eb2   4 seconds ago       207MB
    recommendation-service      1.0       d07d9ae5e9aa   About an hour ago   207MB
    review-service              1.0       e15379e32829   About an hour ago   207MB
    product-service             1.0       f75e4ed58dd9   About an hour ago   207MB

### Run the Container

    docker run -d -p 7000:7000 --name product-composite-service product-composite-service:1.0

### Test

    curl http://localhost:7000/product-composite/1
    
    {"productId":1,"name":"Product-1","weight":100,"reviews":[{"productId":1,"reviewId":1,"author":"Alice","subject":"Great!","content":"Really liked this product"},{"productId":1,"reviewId":2,"author":"Bob","subject":"Good","content":"Does the job well"}],"recommendations":[{"productId":1,"recommendationId":1,"author":"Carol","rating":5,"content":"Higly recommended"},{"productId":1,"recommendationId":2,"author":"Dave","rating":4,"content":"Worth buying"}]}

### Errors?

    # application.properties
    logging.level.com.minte9.microservices=DEBUG

    ./gradlew clean build

    docker stop product-composite-service
    docker rm product-composite-service
    docker rmi procuct-composite-service

    docker build -t product-composite-service:1.0 .
    docker run -d -p 7000:7000 --name product-composite-service product-composite-service:1.

    docker ps -a
    docker compose logs --tail=200 product-composite-service

