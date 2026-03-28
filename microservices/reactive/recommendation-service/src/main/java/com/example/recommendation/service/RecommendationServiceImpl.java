package com.example.recommendation.service;

import java.time.Duration;

import org.springframework.stereotype.Service;

import com.example.recommendation.api.Recommendation;
import com.example.recommendation.persistence.RecommendationEntity;
import com.example.recommendation.persistence.RecommendationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RecommendationServiceImpl implements RecommendationService {

        private final RecommendationRepository repository;

        public RecommendationServiceImpl(RecommendationRepository repository) {
            this.repository = repository;
        }

        @Override
        public Mono<Recommendation> create(Recommendation recommendation) {

            RecommendationEntity entity = new RecommendationEntity(
                null, // let Mongo generate _id
                recommendation.productId(),
                recommendation.recommendationId(),
                recommendation.author(),
                recommendation.rating(),
                recommendation.content()
            );

            return repository.save(entity)
                    .map(e -> new Recommendation(
                        e.productId(),
                        e.recommendationId(),
                        e.author(),
                        e.rating(),
                        e.content()
                    ));
        }

        @Override
        public Flux<Recommendation> getByProductId(int productId) {

            return repository.findByProductId(productId)

                        //.delayElements((Duration.ofSeconds(2)))   // temporary: to prove reactivity
                        .delayElements(Duration.ofMillis(100))      // temporary: for the stress test

                        .map(e -> new Recommendation(
                            e.productId(),
                            e.recommendationId(),
                            e.author(),
                            e.rating(),
                            e.content()
                        ));
            }

        @Override
        public Mono<Void> deleteByProductId(int productId) {
            return repository.deleteByProductId(productId);
        }
}
