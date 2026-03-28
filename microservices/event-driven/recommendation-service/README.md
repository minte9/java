## REACTIVE - RECOMMENDATION MICROSERVICE (WebFlux)

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

        public interface RecommendationRepository 
            extends MongoRepository<RecommendationEntity, String>

    To: 

        public interface RecommendationRepository 
            extends ReactiveMongoRepository<RecommendationEntity, String>


### Make Controller Reactive

    Change:

        @GetMapping
        public List<Recommendation> getRecommendations(...)

    With:

        import reactor.core.publisher.Flux;
        import reactor.core.publisher.Mono;

        public Mono<Recommendation> createRecommendation(...)


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


    curl -X POST http://localhost:7001/product \
        -H "Content-Type: application/json" \
        -d '{"productId": 1, "name": "Product-1", "weight": 100}'

    curl http://localhost:7001/product/1 | jq


    curl -X POST http://localhost:7003/recommendation \
        -H "Content-Type: application/json" \
        -d '{"productId":1,"recommendationId":1,"author":"Carol","rating":5,"content":"Higly recommended"}'

    curl -X POST http://localhost:7003/recommendation \
        -H "Content-Type: application/json" \
        -d '{"productId":1,"recommendationId":2,"author":"Dave","rating":4,"content":"Worth buying"}'

    curl http://localhost:7003/recommendation?productId=1 | jq



### Reactive doesn't mean different JSON

If you see the same JSON response - you are reactive.  

Reactive programming does NOT change:  

    - The endpoint URL
    - The HTTP method
    - The JSON structure
    - The response format

It changes how the server processes requests internally.  

From the client perspective:  

    Blocking REST endpoint -> JSON
    Reactive REST endpoint -> JSON

They look identical.  


#

### Verify reactivity (1) - Netty vs Tomcat

    docker compose down
    docker compose build --no-cache recommendation-service
    docker compose up -d

When your service starts, check logs.  

    docker compose logs -f recommendation-service
        # Netty started on port 7003 (http)

If you see Tomcat (it is still blocking MVC), not good.

    docker compose logs -f recommendation-service
        #Tomcat started on port 7003


### Verify reactivity (2) - Delay

Add delay to the service.

    import java.time.Duration;

    public Flux<Recommendation> getByProductId(int productId) {

        return repository.findByProductId(productId)
                    .delayElements((Duration.ofSeconds(2)))  // temporary: to prove  reactivity
                    .map(e -> new Recommendation(
                        e.productId(),
                        e.recommendationId(),
                        e.author(),
                        e.rating(),
                        e.content()
                    ));
        }

Call the request multiple time in different terminals.
2x2 seconds for every recommendation.

    time curl "http://localhost:7003/recommendation?productId=1"
    real    0m4,043s
    user    0m0,012s
    sys     0m0,006s

In blocking environment 2 request in the same time, will finish in 8 seconds (not 4).  

Reactive does NOT make one request faster.  
It makes many requests scale better.  


### Verify reactivity (3) - Concurrency Stress Test (Advanced)

Add a 100 miliseconds delay.  
This simulates IO latency.

    return repository.findByProductId(productId)
        .delayElements(Duration.ofMillis(100))

If you fire 1000 concurrent requests:

Blocking:

    - Threads grows
    - Memory grows
    - Eventually thread exhaustion

    ab -n 1000 -c 200 http://localhost:7000/product-composite/1

    Concurrency Level:      200
    Time taken for tests:   7.120 seconds
    Complete requests:      1000

    Percentage of the requests served within a certain time (ms)
    50%   1290
    66%   1500
    75%   1621
    80%   1680
    90%   1911
    95%   2076
    98%   2316
    99%   2471
    100%   2990 (longest request)

Reactive:

    - Few threads
    - Handles concurrency without thread explosion

    ab -n 1000 -c 200 http://localhost:7003/recommendation?productId=1

    Concurrency Level:      200
    Time taken for tests:   2.397 seconds
    Complete requests:      1000

    Percentage of the requests served within a certain time (ms)
    50%    397
    66%    429
    75%    449
    80%    463
    90%    540
    95%    687
    98%    750
    99%    766
    100%    787 (longest request)


Look at tail latency (99% and max):  

    Blocking:  
        99% = 2471ms  
        max = 2990ms  

    Reactive:  
        99% = 766ms  
        max = 787ms  

That’s the real win.   

Reactive dramatically reduces latency variance under load.   