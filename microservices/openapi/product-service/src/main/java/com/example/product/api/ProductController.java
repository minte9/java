package com.example.product.api;

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
            entity.getProductId(),
            entity.getName(),
            entity.getWeight()
        );
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {

        ProductEntity entity = 
            new ProductEntity(
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