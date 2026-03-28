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
