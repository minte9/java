package com.minte9.microservices.core.composite.product.api; 

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/product-composite")
public class ProductCompositeController {

        @Value("${product.service.base:http://product-service:7001}")
        private String productServiceBase;

        @Value("${review.service.base:http://review-service:7002}")
        private String reviewServiceBase;

        @Value("${recommendation.service.base:http://recommendation-service:7003}")
        private String recommendationServiceBase;

        private final RestTemplate restTemplate;

        public ProductCompositeController(RestTemplate restTemplate) {
                this.restTemplate = restTemplate;
        }

        @GetMapping("/{productId}")
        public ProductAggregate getProduct(@PathVariable int productId) {

                String productUrl = productServiceBase + "/product/{id}";
                String reviewUrl = reviewServiceBase + "/review?productId={id}";
                String recommendationUrl = recommendationServiceBase + "/recommendation?productId={id}";

                Product product = restTemplate.getForObject(
                        productUrl,
                        Product.class,
                        productId
                );

                Review[] reviews = restTemplate.getForObject(
                        reviewUrl,
                        Review[].class,
                        productId
                );

                Recommendation[] recommendations = restTemplate.getForObject(
                        recommendationUrl,
                        Recommendation[].class,
                        productId
                );

                return new ProductAggregate(
                                product.productId(),
                                product.name(),
                                product.weight(),
                                List.of(reviews),
                                List.of(recommendations)
                );
        }
}
