package com.example.recommendation.service;

import org.springframework.stereotype.Service;

import com.example.recommendation.api.Recommendation;
import com.example.recommendation.persistence.*;
import java.util.List;

@Service
public class RecommendationServiceImpl implements RecommendationService {

        private final RecommendationRepository repository;

        public RecommendationServiceImpl(RecommendationRepository repository) {
            this.repository = repository;
        }

        @Override
        public Recommendation create(Recommendation recommendation) {

            RecommendationEntity entity = new RecommendationEntity(
                null, // let Mongo generate _id
                recommendation.productId(),
                recommendation.recommendationId(),
                recommendation.author(),
                recommendation.rating(),
                recommendation.content()
            );

            repository.save(entity);

            return recommendation;
        }

        @Override
        public List<Recommendation> getByProductId(int productId) {

            return repository.findByProductId(productId)
                        .stream()
                        .map(e -> new Recommendation(
                            e.productId(),
                            e.recommendationId(),
                            e.author(),
                            e.rating(),
                            e.content()
                        ))
                        .toList();
        }

        @Override
        public void deleteByProductId(int productId) {
            repository.deleteByProductId(productId);
        }
}
