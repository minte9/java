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
