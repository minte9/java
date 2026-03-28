## Review Service (Spring CLI)

From the microservices directory:  

### Create the product service:  

    spring init \
    --boot-version=3.5.0 \
    --type=gradle-project \
    --java-version=17 \
    --packaging=jar \
    --package-name=com.minte9.microservices.core.recommendation \
    --groupId=com.minte9.microservices.core.recommendation \
    --dependencies=web,actuator \
    --version=1.0.0 \
    --name=recommendation-review

From inside product-recommendation:

    ./gradlew clean build

        BUILD SUCCESSFUL in 38s
        8 actionable tasks: 7 executed, 1 up-to-date

Add server port and logging:

    src/main/resources/application.properties  

    spring.application.name=recommendation-service
    server.port=7003
    logging.level.com.minte9.microservices=INFO

### Create API Structure:

Api workspace structure:

    com.minte9.microservices.core.recommendation
    ├── RecommendationServiceApplication
    ├── api
    │   ├── Recommendation
    │   └── RecommendationController

Product DTO:

    package com.minte9.microservices.core.recommendation.api;

    public record Recommendation (
        int productId,
        int recommendationId,
        String author,
        int rating,
        String content
    ) {}


Product Controller:

    package com.minte9.microservices.core.recommendation.api;

    import org.springframework.web.bind.annotation.*;
    import java.util.List;

    @RestController
    @RequestMapping("/recommendation")
    public class RecommendationController {
        
        @GetMapping
        public List<Recommendation> getRecommendations(@RequestParam int productId) {

            return List.of(
                new Recommendation(
                    productId, 
                    1, 
                    "Carol",
                    5,
                    "Higly recommended"
                ),
                new Recommendation(
                    productId, 
                    2, 
                    "Dave",
                    4,
                    "Worth buying"
                )
            );
        }
    }

### Run the Service:

    ./gradlew bootRun

### Test the Service

    curl http://localhost:7003/recommendation?productId=1
    
    [{"productId":1,"recommendationId":1,"author":"Carol","rating":5,"content":"Higly recommended"},
     {"productId":1,"recommendationId":2,"author":"Dave","rating":4,"content":"Worth buying"}]

### Health check

    Spring Actuator already exposes /actuator/health.
    
    curl http://localhost:7003/actuator/health
    {"status":"UP"}