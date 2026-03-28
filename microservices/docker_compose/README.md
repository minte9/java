## Docker Deploy

### Create the Dockerfile for each service

    # product-service/Dockerfile

    FROM eclipse-temurin:17-jre-alpine
    WORKDIR /app
    ARG JAR_FILE=build/libs/*.jar
    COPY ${JAR_FILE} app.jar
    EXPOSE 7001
    ENTRYPOINT ["java", "-jar", "/app/app.jar"]

Build the JAR
    
    cd product-service/
    ./gradlew clean build

Build the Docker Image

    docker build -t some-service:1.0 .

Run the container (for each service)

    docker run -d 7001:7001 --name product-service product-service:1.0 .

Test the service

    curl http://localhost:7001/product/1
    {"productId":1,"name":"Product-1","weight":100}


## Docker Compose

Right now the composite service uses:

    http://localhost:7001

This will not work in Docker, we need to replace with environment variables.  
This is mandatory before Docker Compose.

cd product-composite-service/

    # application/src/main/resources/application.properties
    
    app.product-service.host=${PRODUCT_SERVICE_HOST:localhost}
    app.product-service.port=${PRODUCT_SERVICE_PORT:7001}

Docker Compose lets you:

    - Start all services together
    - Share a network
    - Refer to services by name, not IP

docker-compose.yml (root folder)

    version: "3.9"

    services:
        product-service:
        image: product-service:1.0
        ports:
            - "7001:7001"
    
    review-service:
        image: review-service:1.0
        ports:
            - "7002:7002"
    
    recommendation-service:
        image: recommendation-service:1.0
        ports:
            - "7003:7003"
    
    product-composite-service:
        image: product-composite-service:1.0
        ports:
            - "7000:7000"
        environment:
            PRODUCT_SERVICE_HOST: product-service
            REVIEW_SERVICE_HOST: review-service
            RECOMMENDATION_SERVICE_HOST: recommendation-service
        depends_on:
          - product-service
          - review-service
          - recommendation-service

### Run everything
    
    cd docker_deploy/product-composite-service/
    ./gradlew clean build
    docker build -t product-composite-service:1.0 .
    
    cd docker_deploy/
    docker compose down
    docker compose up -d

    docker compose logs --tail=200 product-composite-service

### Test the Composite Service

    curl http://localhost:7001/product/1
    curl http://localhost:7002/review?productId=1
    curl http://localhost:7003/recommendation?productId=1
    curl http://localhost:7000/product-composite/1

    {"productId":1,"name":"Product-1","weight":100, 
     "reviews":[
        {"productId":1,"reviewId":1,"author":"Alice","subject":"Great!","content":"Really liked this product"},
        {"productId":1,"reviewId":2,"author":"Bob","subject":"Good","content":"Does the job well"}],
    "recommendations":[
        {"productId":1,"recommendationId":1,"author":"Carol","rating":5,"content":"Higly recommended"},
        {"productId":1,"recommendationId":2,"author":"Dave","rating":4,"content":"Worth buying"}]}