package com.example.recommendation.api;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.recommendation.service.RecommendationService;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    public final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }
    
    @GetMapping
    public List<Recommendation> getRecommendations(
        @RequestParam int productId) {

        return service.getByProductId(productId);
    }

    @PostMapping
    public Recommendation creatRecommendation(
        @RequestBody Recommendation recommendation) {
        
        return service.create(recommendation);
    }

    @DeleteMapping
    public void deleteRecommendations(
        @RequestParam int productId) {
        
        service.deleteByProductId(productId);
    }
}
