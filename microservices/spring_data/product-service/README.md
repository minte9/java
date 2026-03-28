## PRODUCT SERVICE (MongoDB)

Add MongoDB dependency using Spring Data.


### Folder structure

    product/
    ├── api/
    │     ├── Product.java
    │     └── ProductController.java
    │
    ├── persistence/
    │     ├── ProductEntity.java
    │     └── ProductRepository.java
    │
    └── ProductServiceApplication.java


### Docker Compose

Add to build.gradle:
    
    implement 'org.springframework.boot:spring-boot-starter-data-mongodb'

Configure application.properties.

    spring.application.name=product-service
    server.port=7001

    spring.data.mongodb.host=${SPRING_DATA_MONGODB_HOST:localhost}
    spring.data.mongodb.port=${SPRING_DATA_MONGODB_PORT:27017}
    spring.data.mongodb.database=product-db

Add MongoDB to docker-compose in composite project.

    services:

        mongodb:
            image: mongo:7.0
            container_name: mongodb
            ports:
                - "27017:27017"
            environment:
                MONGO_INITDB_DATABASE: product-db
            volumes:
              - mongodb-data:/data/db
        
        product-service:
            image: product-service:1.0
            container_name: product-service
            ports:
                - "7001:7001"
            depends_on:
                - mongodb
            environment:
                SPRING_DATA_MONGODB_HOST: mongodb
                SPRING_DATA_MONGODB_PORT: 27017
        volumes:
            mongodb-data:
    
Rebuild Docker images.

    ./gradlew build
    docker build -t product-service:1.0 .
    docker images
        REPOSITORY                  TAG       IMAGE ID       CREATED              SIZE
        product-service             1.0       3854eaa7ddd9   About a minute ago   214MB



### Review Entity

Records works fine with Spring Data (MongoDB).
Create review entity file. 

    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;

    @Document(collection = "products")
    public class ProductEntity {

        @Id 
        private String id;

        private int productId;
        private String name;
        private int weight;

        public ProductEntity() {}

        public ProductEntity(int productId, String name, int weight) {
            this.productId = productId;
            this.name = name;
            this.weight = weight;
        }

        public String getId() { return id; }
        public int getProductId() { return productId; }
        public String getName() { return name; }
        public int getWeight() { return weight; }
    }


### Repository

Create repository.

    import org.springframework.data.mongodb.repository.MongoRepository;

    public interface ProductRepository 
        extends MongoRepository<ProductEntity, String> {

        ProductEntity findByProductId(int productId);

    }


### Review Controller

Direct repository injection.

    import org.springframework.web.bind.annotation.*;
    import com.example.product.persistence.ProductEntity;
    import com.example.product.persistence.ProductRepository;

    @RestController
    @RequestMapping("/product")
    public class ProductController {

        private final ProductRepository repository;

        public ProductController(ProductRepository repository) {
            this.repository = repository;
        }
        
        @GetMapping("/{productId}")
        public Product getProduct(@PathVariable int productId) {
            
            ProductEntity entity = repository.findByProductId(productId);

            if (entity == null) {
                throw new RuntimeException("Product not found");
            }

            return new Product(
                entity.getProductId(),
                entity.getName(),
                entity.getWeight()
            );
        }

        @PostMapping
        public Product createProduct(@RequestBody Product product) {

            ProductEntity entity = 
                new ProductEntity(
                        product.productId(),
                        product.name(),
                        product.weight()
                );

            repository.save(entity);

            return product;
        }

        @DeleteMapping("/{productId}")
        public void deleteProduct(@PathVariable int productId) {

            ProductEntity entity = repository.findByProductId(productId);

            if (entity == null) {
                throw new RuntimeException("Product not found");
            }

            repository.delete(entity);
        }

    }


### Build the image and start containers

When you modify code in src/, this is the correct workflow.

    - Stop everything

        docker compose down
        docker ps

    - Rebuild jar (because docker image contains the jar)
        
        ./gradlew clean build

    - Rebuild Docker images

        docker compose build

    - Start containers
        
        docker compose up -d

### If not working

    docker compose down -v
    docker container prune -f
    docker image prune -f

    ./gradlew clean build
    docker compose up --build -d

### Test it

    curl -X POST http://localhost:7001/product \
    -H "Content-Type: application/json" \
    -d '{
            "productId": 1,
            "name": "Product-1",
            "weight": 100
        }'

    curl http://localhost:7001/product/1
        
    curl -X DELETE http://localhost:7001/product/1

    curl http://localhost:7002/review?productId=1
        500 error: Internal Server error