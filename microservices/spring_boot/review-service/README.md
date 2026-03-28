## Review Service (Spring CLI)

From the microservices directory:  

### Create the product service:  

    spring init \
    --boot-version=3.5.0 \
    --type=gradle-project \
    --java-version=17 \
    --packaging=jar \
    --package-name=com.minte9.microservices.core.review \
    --groupId=com.minte9.microservices.core.review \
    --dependencies=web,actuator \
    --version=1.0.0 \
    --name=product-review

From inside product-review:

    ./gradlew clean build

    BUILD SUCCESSFUL in 38s
    8 actionable tasks: 7 executed, 1 up-to-date

Add server port and logging:

    src/main/resources/application.properties  

    spring.application.name=review-service
    server.port=7002
    logging.level.com.minte9.microservices=INFO

### Create Review API Structure:

Api workspace structure:

    com.minte9.microservices.core.review
    ├── ReviewServiceApplication
    ├── api
    │   ├── Review
    │   └── ReviewController

Product DTO:

    package com.minte9.microservices.core.review.api;

    public record Review (
        int productId,
        int reviewId,
        String author, 
        String subject,
        String content
    ) {}


Product Controller:

    package com.minte9.microservices.core.review.api;

    import org.springframework.web.bind.annotation.*;
    import java.util.List;

    @RestController
    @RequestMapping("/review")
    public class ReviewController {
        
        @GetMapping
        public List<Review> getReviews(@RequestParam int productId) {

            return List.of(
                new Review(
                    productId, 
                    1, 
                    "Alice", 
                    "Great!", 
                    "Really liked this product"
                ),
                new Review(
                    productId,
                    2,
                    "Bob",
                    "Good",
                    "Does the job well"
                )
            );
        }
    }


### Run the Service:

    ./gradlew bootRun

### Test the Service

    curl http://localhost:7002/review?productId=1
    
    [{"productId":1,"reviewId":1,"author":"Alice","subject":"Great!","content":"Really liked this product"},
     {"productId":1,"reviewId":2,"author":"Bob","subject":"Good","content":"Does the job well"}]

5) Health check

    Spring Actuator already exposes /actuator/health.
    
    curl http://localhost:7002/actuator/health

        {"status":"UP"}