## REACTIVE - REVIEW MICROSERVICE (WebFlux)

In Spring Boot 3, reactive =

    - Spring WebFlux (instead of MVC)
    - Non-blocking HTTP layer
    - Reactive MongoDB driver
    - Return types: Mono<T> and Flux<T>

### Reactive Dependencies

In build.gradle replace:

    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb'

With:

    implementation 'org.springframework.boot:spring-boot-starter-webflux'
    implementation 'org.springframework.boot:spring-boot-starter-data-mongodb-reactive'
    implementation 'org.springdoc:springdoc-openapi-starter-webflux-ui:2.5.0'


### What Changes Under the Hood?

    Before: 

        Tomcat  
        Blocking  
        MongoRepository  
        List  
        Object  

    After:  

        Netty  
        Non-blocking  
        ReactiveMongoRepository  
        Flux  
        Mono  


### Replace Repository

    Change:

        public interface ReviewRepository 
            extends MongoRepository<ReviewEntity, String>

    To: 

        public interface ReviewRepository 
            extends ReactiveMongoRepository<ReviewEntity, String>


### Make Controller Reactive

    Change:

        @GetMapping
        public List<Review> getReviews(...)

    With:

        import reactor.core.publisher.Flux;
        import reactor.core.publisher.Mono;

        public Mono<Review> createReview(...)


### Modify Service Layer

    Change:

        Recommendation create(Recommendation recommendation);
        List<Recommendation> getByProductId(int productId);
        void deleteByProductId(int productId);

    Width:

        Mono<Recommendation> create(Recommendation recommendation);
        Flux<Recommendation> getByProductId(int productId);
        Mono<Void> deleteByProductId(int productId);


### Verify

    docker compose down -v
    ./gradlew clean build -x test
    docker compose up --build -d

    docker exec -it mongodb mongosh
    use review-db
    db.reviews.insertMany([
        { productId: 1, reviewId: 1, author: "Alice", subject: "Great", content: "Liked"},
        { productId: 1, reviewId: 2, author: "Bob", subject: "Good", content: "Fine" },
        { productId: 1, reviewId: 3, author: "Chris", subject: "Small", content: "NOK" }
    ])

    curl http://localhost:7000/product-composite/1 | jq


## Verify reactivity (Netty)

    docker compose logs -f review-service
        # Netty started on port 7003 (http)