package com.example.review.service;

import com.example.review.api.Review;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReviewService {

    Mono<Review> create(Review review);
    Flux<Review> getByProductId(int productId);
    Mono<Void> deleteByProductId(int productId);
}
