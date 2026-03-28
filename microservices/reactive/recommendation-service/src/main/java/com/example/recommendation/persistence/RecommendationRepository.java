package com.example.recommendation.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecommendationRepository 
    extends ReactiveMongoRepository<RecommendationEntity, String> {

        Flux<RecommendationEntity> findByProductId(int productId);
        Mono<Void> deleteByProductId(int productId);
    
}
