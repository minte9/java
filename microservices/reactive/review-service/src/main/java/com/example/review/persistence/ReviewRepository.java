package com.example.review.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewRepository
        extends ReactiveMongoRepository<ReviewEntity, String> {

    Flux<ReviewEntity> findByProductId(int productId);
    Mono<Void> deleteByProductId(int productId);

}
