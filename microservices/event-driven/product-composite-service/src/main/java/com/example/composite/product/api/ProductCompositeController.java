package com.example.composite.product.api; 

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
