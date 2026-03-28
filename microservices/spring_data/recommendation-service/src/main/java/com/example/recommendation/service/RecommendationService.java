package com.example.recommendation.service;

import com.example.recommendation.api.Recommendation;
import java.util.List;

public interface RecommendationService {

    Recommendation create(Recommendation recommendation);
    
    List<Recommendation> getByProductId(int productId);
    
    void deleteByProductId(int productId);
}
