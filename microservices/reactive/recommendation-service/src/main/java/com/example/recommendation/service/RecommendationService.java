package com.example.recommendation.service;

import com.example.recommendation.api.Recommendation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RecommendationService {

    Mono<Recommendation> create(Recommendation recommendation);
    Flux<Recommendation> getByProductId(int productId);
    Mono<Void> deleteByProductId(int productId);
}
