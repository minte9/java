## RECOMMENDATION SERVICE (MongoDB)

Add MongoDB dependency using Spring Data.

### Service folder structure

    com.example.recommendation
    │
    ├── api
    │   ├── Recommendation.java
    │   └── RecommendationController.java
    │
    ├── persistence
    │   ├── RecommendationEntity.java
    │   └── RecommendationRepository.java
    │
    ├── service
    │   ├── RecommendationService.java
    │   └── RecommendationServiceImpl.java
    │
    └── RecommendationServiceApplication.java


Add to build.gradle:
    
    implement 'org.springframework.boot:spring-boot-starter-data-mongodb'


Configure application.properties.

    spring.application.name=recommendation-service
    server.port=7001

    spring.data.mongodb.host=${SPRING_DATA_MONGODB_HOST:localhost}
    spring.data.mongodb.port=${SPRING_DATA_MONGODB_PORT:27017}
    spring.data.mongodb.database=recommendation-db


### Convert Domain Model to MongoDB

Create Mongo Entity (record).

    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;
    import org.springframework.data.mongodb.core.index.Indexed;

    @Document(collection = "recommendations")
    public record RecommendationEntity (
        @Id String id,

        @Indexed
        int productId,  // index improves query performance

        int recommendationId,
        String author,
        int rating,
        String content
    ) {}

Enable index creation in application.properties.

    spring.data.mongodb.auto-index-creation=true

Create Repository.

    import org.springframework.data.mongodb.repository.MongoRepository;
    import java.util.List;

    public interface RecommendationRepository 
        extends MongoRepository<RecommendationEntity, String> {
            List<RecommendationEntity> findByProductId(int productId);
            void deleteByProductId(int productId);
        
    }

### Service Interface

    import com.example.recommendation.api.Recommendation;
    import java.util.List;

    public interface RecommendationService {
        Recommendation create(Recommendation recommendation);
        List<Recommendation> getByProductId(int productId);
        void deleteByProductId(int productId);
    }


### Service Implementation

    import org.springframework.stereotype.Service;
    import java.util.List;

    import com.example.recommendation.api.Recommendation;
    import com.example.recommendation.persistence.*;

    public class RecommendationServiceImpl implements RecommendationService {

            private final RecommendationRepository repository;

            public RecommendationServiceImpl(RecommendationRepository repository) {
                this.repository = repository;
            }

            @Override
            public Recommendation create(Recommendation recommendation) {

                RecommendationEntity entity = new RecommendationEntity(
                    null, // let Mongo generate _id
                    recommendation.productId(),
                    recommendation.recommendationId(),
                    recommendation.author(),
                    recommendation.rating(),
                    recommendation.content()
                );

                repository.save(entity);

                return recommendation;
            }

            @Override
            public List<Recommendation> getByProductId(int productId) {

                return repository.findByProductId(productId)
                            .stream()
                            .map(e -> new Recommendation(
                                e.productId(),
                                e.recommendationId(),
                                e.author(),
                                e.rating(),
                                e.content()
                            ))
                            .toList();
            }

            @Override
            public void deleteByProductId(int productId) {
                repository.deleteByProductId(productId);
            }
    }


### Controller

Controller does nothing except call service.  
That's clean separation.  

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
                entity.productId(),
                entity.name(),
                entity.weight()
            );
        }
    
        @PostMapping
        public Product createProduct(@RequestBody Product product) {
    
            ProductEntity entity = 
                new ProductEntity(
                        null, // let Mongo generate _id
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
Disable tests temporary (because it will try to connect to MongoDB).  

    docker compose down
    docker ps

    ./gradlew build -x test
    docker compose build

    docker compose up -d

    curl http://localhost:7003/recommendation?productId=1
    []


### Populate mongodb

    curl -X POST http://localhost:7003/recommendation \
        -H "Content-Type: application/json" \
        -d '{"productId":1,"recommendationId":1,"author":"Carol","rating":5,"content":"Higly recommended"}'

    curl -X POST http://localhost:7003/recommendation \
        -H "Content-Type: application/json" \
        -d '{"productId":1,"recommendationId":2,"author":"Dave","rating":4,"content":"Worth buying"}'


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
        "reviews": [
        {
            "productId": 1,
            "reviewId": 1,
            "author": "Alice",
            "subject": "Great",
            "content": "Liked"
        },
        {
            "productId": 1,
            "reviewId": 2,
            "author": "Bob",
            "subject": "Good",
            "content": "Fine"
        },
        {
            "productId": 1,
            "reviewId": 3,
            "author": "Chris",
            "subject": "Small",
            "content": "NOK"
        }
    ],
    "recommendations": [
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
    ]}
    