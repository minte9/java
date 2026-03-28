package com.example.product.api;

import org.springframework.web.bind.annotation.*;
import com.example.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/product")
public class ProductController {

    public final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public Mono<Product> getProduct(
            @PathVariable int productId) {

        return service.getProduct(productId);
    }

    @Operation(summary = "Create a product",
            description = "Create a product for a specific product")
    @PostMapping
    public Mono<Product> creatProduct(
            @RequestBody Product product) {

        return service.create(product);
    }

    @DeleteMapping("/{productId}")
    public Mono<Void> deleteProduct(
            @PathVariable int productId) {

        return service.delete(productId);
    }
}
