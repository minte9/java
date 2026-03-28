## REACTIVE - PRODUCT MICROSERVICE (WebFlux)

In Spring Boot 3, reactive =

    - Spring WebFlux (instead of MVC)
    - Non-blocking HTTP layer
    - Reactive MongoDB driver
    - Return types: Mono<T> and Flux<T>

### Reactive Dependencies

In build.gradle replace:

    implementation 'org.springframework.boot:spring-boot-starter-web'

With:

    implementation 'org.springframework.boot:spring-boot-starter-webflux'
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


### Create a new config

Replace RestTemplateConfig with WebClientConfig.

    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.web.reactive.function.client.WebClient;

    @Configuration
    public class WebClientConfig {

        @Bean
        public WebClient.Builder webClientBuilder() {
            return WebClient.builder();
        }
    }


### Make Controller Reactive

Import:

    import reactor.core.publisher.Mono;
    import reactor.core.publisher.Flux;

Replace the ProductCompositeController.

    import org.springframework.beans.factory.annotation.Value;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.PathVariable;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;
    import org.springframework.web.reactive.function.client.WebClient;

    import reactor.core.publisher.Flux;
    import reactor.core.publisher.Mono;

    @RestController
    @RequestMapping("/product-composite")
    public class ProductCompositeController {

            @Value("${product.service.base:http://product-service:7001}")
            private String productServiceBase;

            @Value("${review.service.base:http://review-service:7002}")
            private String reviewServiceBase;

            @Value("${recommendation.service.base:http://recommendation-service:7003}")
            private String recommendationServiceBase;

            private final WebClient.Builder webClientBuilder;

            public ProductCompositeController(WebClient.Builder webClientBuilder) {
                    this.webClientBuilder = webClientBuilder;
            }

            @GetMapping("/{productId}")
            public Mono<ProductAggregate> getProduct(@PathVariable int productId) {

                    WebClient client = webClientBuilder.build();

                    Mono<Product> productMono = 
                            client.get()
                                .uri(productServiceBase + "/product/{id}", productId)
                                .retrieve()
                                .bodyToMono(Product.class);

                    Flux<Review> reviewFlux = 
                            client.get()
                                .uri(reviewServiceBase + "/review?productId={id}", productId)
                                .retrieve()
                                .bodyToFlux(Review.class);

                    Flux<Recommendation> recommendationFlux = 
                            client.get()
                                .uri(recommendationServiceBase + "/recommendation?productId={id}", productId)
                                .retrieve()
                                .bodyToFlux(Recommendation.class);

                    return Mono.zip(
                            productMono,
                            reviewFlux.collectList(),
                            recommendationFlux.collectList()
                    )
                    .map(tuple -> new ProductAggregate(
                            tuple.getT1().productId(),
                            tuple.getT1().name(),
                            tuple.getT1().weight(),
                            tuple.getT2(),
                            tuple.getT3()
                    ));
            }
    }

Instead of:

    call A -> wait
    call B -> wait
    call C -> wait

We now:

    - Start all three calls immediately
    - Combine results when all complete
    - Never block a thread


### Verify

    docker compose down
    docker compose build --no-cache product-service
    docker compose build --no-cache product-composite-service
    docker compose up -d --build


## Verify reactivity (Netty)

You must see:

    Netty started on port 7000

    docker compose logs -f product-composite-service


## Test the service

    curl http://localhost:7000/product-composite/1