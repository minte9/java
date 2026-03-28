package com.example.product.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductRepository
        extends ReactiveMongoRepository<ProductEntity, String> {

    Flux<ProductEntity> findByProductId(int productId);
    Mono<Void> deleteByProductId(int productId);

}
