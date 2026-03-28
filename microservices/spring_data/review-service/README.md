## REVIEW SERVICE (MongoDB)

Add MongoDB dependency using Spring Data.

### Service folder structure

Keep your original structure and add persistence:

    product/
    ├── api/
    │     ├── Review.java
    │     └── ReviewController.java
    │
    ├── persistence/
    │     ├── ReviewEntity.java
    │     └── ReviewRepository.java
    │
    └── ProductReviewServiceApplication.java


### Docker Compose

Add MongoDB to docker-compose.

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
        
        review-service:
            image: review-service:1.0
            container_name: review-service
            ports:
                - "7002:7002"
            depends_on:
                - mongodb
            environment:
                SPRING_DATA_MONGODB_HOST: mongodb
                SPRING_DATA_MONGODB_PORT: 27017
        ...
        volumes:
            mongodb-data:

Add to build.gradle:
    
    implement 'org.springframework.boot:spring-boot-starter-data-mongodb'

Configure application.properties.

    spring.application.name=product-service
    server.port=7001

    spring.data.mongodb.host=${SPRING_DATA_MONGODB_HOST:localhost}
    spring.data.mongodb.port=${SPRING_DATA_MONGODB_PORT:27017}
    spring.data.mongodb.database=product-db

Rebuild Docker images.

    ./gradlew build
    docker build -t review-service:1.0 .
    docker images
        REPOSITORY                  TAG       IMAGE ID       CREATED              SIZE
        review-service             1.0       3854eaa7ddd9   About a minute ago   214MB



### Review Entity & Repository

Records works fine with Spring Data (MongoDB).
Create review entity file. 

    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;

    @Document(collection = "reviews")
    public record ReviewEntity (
        @Id String id,

        int productId,
        int reviewId,
        String author,
        String subject,
        String content
    ) {}

Create repository.

    import org.springframework.data.mongodb.repository.MongoRepository;
    import java.util.List;

    public interface ReviewRepository
        extends MongoRepository<ReviewEntity, String> {

        List<ReviewEntity> findByProductId(int productId);

        void deleteByProductId(int productId);
    }

### Review Controller

Modify controller by injecting repository and map entities.

    package com.example.review.api;

    import com.example.review.persistence.ReviewEntity;
    import com.example.review.persistence.ReviewRepository;
    import org.springframework.web.bind.annotation.*;
    import java.util.List;

    @RestController
    @RequestMapping("/review")
    public class ReviewController {
        
        private final ReviewRepository repository;

        public ReviewController(ReviewRepository repository) {
            this.repository = repository;
        }

        @GetMapping
        public List<Review> getReviews(@RequestParam int productId) {

            List<ReviewEntity> entities = repository.findByProductId(productId);

            return entities.stream()
                    .map(e -> new Review(
                        e.productId(), 
                        e.reviewId(),
                        e.author(),
                        e.subject(),
                        e.content()
                    ))
                    .toList();
        }

        @PostMapping
        public Review createReview(@RequestBody Review review) {

            ReviewEntity entity = new ReviewEntity(
                null, // let Mongo generate _id
                review.productId(),
                review.reviewId(),
                review.author(),
                review.subject(),
                review.content()
            );

            repository.save(entity);

            return review;
        }

        @DeleteMapping
        public void deleteReviews(@RequestParam int productId) {
            repository.deleteByProductId(productId);
        }
    }


### Build the image and start containers

When you modify code in src/, this is the correct workflow.

    # Stop everything.
    docker compose down
    docker ps

    # Rebuild jar (because docker image contains the jar).
    ./gradlew clean build

    # Rebuild Docker images.
    docker compose build

    # Start containers
    docker compose up -d

### If not working

    docker compose down -v
    docker container prune -v
    docker image prune -f

    ./gradlew clean build
    docker compose up --build -d

### Test it

    curl -X POST http://localhost:7002/review \
        -H "Content-Type: application/json" \
        -d '{
            "productId":1,
            "reviewId":1,
            "author":"author_1",
            "subject":"subject_1",
            "content":"content_1"
        }'

    curl http://localhost:7002/review?productId=1
        [{"productId":1,"reviewId":1,"author":"author_1","subject":"subject_1","content":"content_1"}

    curl -X DELETE http://localhost:7002/review?productId=1

    curl http://localhost:7002/review?productId=1
        []

### Faster testing (Mongo Shell)

Run mongo from inside the container.

    docker exec -it mongodb mongosh
    use review-db
    
    show collections
        # reviews

    db.reviews.insertOne({ productId: 1, reviewId: 1, author: "Alice", subject: "Great", content: "Liked"})

Insert multiple at once:

    db.reviews.insertMany([
        { productId: 1, reviewId: 1, author: "Alice", subject: "Great", content: "Liked"},
        { productId: 1, reviewId: 2, author: "Bob", subject: "Good", content: "Fine" },
        { productId: 1, reviewId: 3, author: "Chris", subject: "Small", content: "NOK" }
    ])

Check what's inside:

    db.reviews.find().pretty()

Find by product:

    db.reviews.find({ productId: 1 }).pretty()

Delete reviews:

    db.reviews.deleteMany({ productId: 1 })
    db.reviews.deleteOne({ reviewId: 2 })
    db.reviews.deleteMany({})


### Pretty print

    sudo apt install jq

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
    ]
    }