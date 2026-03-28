## RECOMMENDATION SERVICE (OpenAPI)

Replace application.properties with application.yml

    server:
        port: 003

    spring:
        application:
            name: recommendation-service

    data:
        mongodb:
        host: mongodb
        port: 27017
        database: recommendation-db

    logging:
        level:
            root: INFO
            org.springframework.data.mongodb.core.MongoTemplate: DEBUG


### Build the image and start containers

When you modify code in src/, this is the correct workflow.  
Disable tests temporary (because it will try to connect to MongoDB).  

    docker compose down
    ./gradlew clean build -x test
    docker compose up --build -d

    curl http://localhost:7003/recommendation?productId=1
    []


### Populate mongodb

    curl -X POST http://localhost:7003/recommendation \
        -H "Content-Type: application/json" \
        -d '{"productId":1,"recommendationId":1,"author":"Carol","rating":5,"content":"Higly recommended"}'

    curl -X POST http://localhost:7003/recommendation \
        -H "Content-Type: application/json" \
        -d '{"productId":1,"recommendationId":2,"author":"Dave","rating":4,"content":"Worth buying"}'

    curl -X POST http://localhost:7001/product \
        -H "Content-Type: application/json" \
        -d '{"productId": 1, "name": "Product-1", "weight": 100}'


### Test Service

    curl http://localhost:7003/recommendation?productId=1 | jq
    [
        {
            "productId": 1,
            "recommendationId": 1,
            "author": "Carol",
            "rating": 5,
            "content": "Higly recommended"
        },
        {
            "productId": 1,
            "recommendationId": 2,
            "author": "Dave",
            "rating": 4,
            "content": "Worth buying"
        }
    ]

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

    http://localhost:7003/v3/api-docs
    http://localhost:7003/swagger-ui/index.html

### Improve Documentation (Annotations)

Right now SpringDoc auto-generates docs.  
But professional APIs include descriptions.  

    import io.swagger.v3.oas.annotations.Operation;

    @Operation(summary = "Create a recommendation",
           description = "Creates a recommendation for a specific product")
    @PostMapping
    public Recommendation createRecommendation(
            @RequestBody Recommendation recommendation) {
        return service.createRecommendation(recommendation);
    }

You can also annotate models.

    import io.swagger.v3.oas.annotations.media.Schema;
    
    @Schema(description = "Recommendation entity")
    public record Recommendation (
        @Schema(description = "Product ID")
        int productId,
    
        @Schema(description = "Recommendation ID")
        int recommendationId,
    
        String author,
        int rating,
        String content
    ) {}

Customize OpenAPI Info by creating a config class.  

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import io.swagger.v3.oas.models.OpenAPI;
    import io.swagger.v3.oas.models.info.Info;
    
    @Configuration
    public class OpenApiConfig {
    
        @Bean
        public OpenAPI customOpenAPI() {
            return new OpenAPI()
                    .info(new Info()
                        .title("Recommendation Service API")
                        .version("1.0")
                        .description("Microservice handling product recommendations")
                    );
        }
    }

### Access Swagger UI
    
    docker compose down
    ./gradlew build -x test && docker compose up --build -d

    http://localhost:7003/v3/api-docs
    http://localhost:7003/swagger-ui/index.html