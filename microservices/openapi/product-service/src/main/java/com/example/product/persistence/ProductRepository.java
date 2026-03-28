package com.example.product.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository
        extends MongoRepository<ProductEntity, String> {

    ProductEntity findByProductId(int productId);
}