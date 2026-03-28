## Product Service (Spring CLI)

From the microservices directory:  

### Create the product service:  

    spring init \
    --boot-version=3.5.0 \
    --type=gradle-project \
    --java-version=17 \
    --packaging=jar \
    --package-name=com.minte9.microservices.core.product \
    --groupId=com.minte9.microservices.core.product \
    --dependencies=web,actuator \
    --version=1.0.0 \
    --name=product-service

From inside product-service:

    ./gradlew clean build

        BUILD SUCCESSFUL in 38s
        8 actionable tasks: 7 executed, 1 up-to-date

Add server port and logging:

    src/main/resources/application.properties  

    spring.application.name=product-service
    server.port=7001
    logging.level.com.minte9.microservices=INFO

### Create the Product API:

Api workspace structure:

    com.minte9.microservices.core.product
    ├── ProductServiceApplication
    ├── api
    │   ├── Product
    │   └── ProductController

Product DTO:

    package com.minte9.microservices.core.product.api;

    public record Product(
            int productId,
            String name,
            int weight
    ) {}

Product Controller:

    package com.minte9.microservices.core.product.api;

    import org.springframework.web.bind.annotation.*;

    @RestController
    @RequestMapping("/product")
    public class ProductController {
        
        @GetMapping("/{productId}")
        public Product getProduct(@PathVariable int productId) {
            
            return new Product(
                productId,
                "Product-" + productId,
                100
            );
        }
    }

### Run the Service:

    ./gradlew bootRun

### Test the Service

    curl http://localhost:7001/product/1
    
    {"productId":1,"name":"Product-1","weight":100}

### Health check

    Spring Actuator already exposes /actuator/health.
    
    curl http://localhost:7001/actuator/health

        {"status":"UP"}