## SPRING-DOC (OpenAPI)

Replace config .properties with .yml

cd product/composite-service/
application.yml

    server:
        port: 7000
        
    spring:
        application:
            name: product-composite-service


### Build the image and start containers

When you modify code in src/, this is the correct workflow.  
Disable tests temporary (because it will try to connect to MongoDB).  

    docker compose down
    ./gradlew clean build -x test
    docker compose up --build -d

    curl http://localhost:7000/product-composite/1 | jq
    {
        "productId": 1,
        "name": "Product-1",
        "weight": 100,
        "reviews": [],
        "recommendations": [
            {
            "productId": 1,
            "recommendationId": 1,
            "author": "Carol",
            "rating": 5,
            "content": "Higly recommended"
            }
        ]
        }


### Add dependency

Inside build.gradle:

    implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0'

    docker compose down
    ./gradlew clean build -x test
    docker compose up --build -d

    http://localhost:7003/actuator/health
    UP

### Access Swagger UI

    http://localhost:7000/v3/api-docs
    http://localhost:7000/swagger-ui/index.html