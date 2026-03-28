### Product-Composite Service (Spring CLI)

From the microservices directory:  

1) Create the service:  

    spring init \
    --boot-version=3.5.0 \
    --type=gradle-project \
    --java-version=17 \
    --packaging=jar \
    --package-name=com.minte9.microservices.core.composite.product \
    --groupId=com.minte9.microservices.core.composite.product \
    --dependencies=web,actuator \
    --version=1.0.0 \
    --name=product-composite-service

From inside product-composite-service:

    ./gradlew clean build

        BUILD SUCCESSFUL in 38s
        8 actionable tasks: 7 executed, 1 up-to-date

Add server port and logging:

    src/main/resources/application.properties  

    spring.application.name=product-composite-service
    server.port=7000
    logging.level.com.minte9.microservices=INFO


2) Package Structure (important)

Create this structure:

    com.minte9.microservices.composite.product
    ├── ProductCompositeServiceApplication
    ├── api
    │   ├── ProductAggregate
    │   ├── Product
    │   ├── Review
    │   ├── Recommendation
    │   └── ProductCompositeController

Note:

    We duplicate DTOs `on purpose`.

Product:  

    package com.minte9.microservices.core.composite.product.api;
    public record Product(
        int productId,
        String name,
        int weight
    ) {}

Review:

    package com.minte9.microservices.core.composite.product.api;
    public record Review(
        int productId,
        int reviewId,
        String author,
        String subject,
        String content
    ) {}

Recommendation:

    package com.minte9.microservices.core.composite.product.api;
    public record Recommendation(
        int productId,
        int recommendationId,
        String author,
        int rating,
        String content
    ) {}

ProductAggregate (the response model):

    package com.minte9.microservices.core.composite.product.api;
    import java.util.List;
    public record ProductAggreagate(
        int productId,
        String name,
        int weight,
        List<Review> reviews,
        List<Recommendation> recommendations    
    ) {}

    // This is what your clients will consume


### 3) Composite Controller (core):

    package com.minte9.microservices.core.composite.product.api;

    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.client.RestTemplate;
    import java.util.List;

    @RestController
    @RequestMapping("/product-composite")
    public class ProductCompositeController {
        
        private final RestTemplate restTemplate = new RestTemplate();

        @GetMapping("/{productId}")
        public ProductAggreagate getProduct(@PathVariable int productId) {

            // 1. Get product
            Product product = restTemplate.getForObject(
                "http://localhost:7001/product/{id}",
                Product.class,
                productId
            );

            // 1. Get product
            Review[] reviews = restTemplate.getForObject(
                "http://localhost:7002/review?productId={id}",
                Review[].class,
                productId
            );

            // 1. Get product
            Recommendation[] recommendations = restTemplate.getForObject(
                "http://localhost:7003/recommendation?productId={id}",
                Recommendation[].class,
                productId
            );

            return new ProductAggreagate(
                product.productId(),
                product.name(),
                product.weight(),
                List.of(reviews),
                List.of(recommendations)
            );
        }
    }



4) Run the Service:

    ./gradlew bootRun

5) Test the Service

    curl http://localhost:7000/product-composite/1
    
        {"productId":1,"name":"Product-1","weight":100,"reviews":[{"productId":1,"reviewId":1,"author":"Alice","subject":"Great!","content":"Really liked this product"},{"productId":1,"reviewId":2,"author":"Bob","subject":"Good","content":"Does the job well"}],"recommendations":[{"productId":1,"recommendationId":1,"author":"Carol","rating":5,"content":"Higly recommended"},{"productId":1,"recommendationId":2,"author":"Dave","rating":4,"content":"Worth buying"}]}

6) It Works!

    You just build the first real composite microservice.

7) Kill a service

Stop review-service.

    curl http://localhost:7000/product-composite/1

    {"timestamp":"2026-02-07T14:04:14.527+00:00","status":500,"error":"Internal Server Error","path":"/product-composite/1"}


### 8) Note on DTO 

    DTO = Data Transfer Object

We duplicated the DTOs on purpose.  
There are API contracts (not domain objects, not shared models).  

They represent:

    "This is the shape of JSON I expect to send/receive".

Sharing DTOs is a TRAP:

    common-api-module/
    └── Product.java

What breaks:

    - A change in product-service breaks product-composite
    - Services must be release together (because the sharing)
    - Versioning becomes painful

With sharing we don't have microservices, we have a distributed monolith.  


### Domain Model vs API Model (important distinction)

    ProductEntity - domain (database, business rules)
    Product - API model

The composite service:

    - Does not own the service
    - Does not enforce business rules
    - Only aggreate data