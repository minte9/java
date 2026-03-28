package com.example.recommendation.api;

import org.springframework.web.bind.annotation.*;
import com.example.recommendation.service.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {

    public final RecommendationService service;

    public RecommendationController(RecommendationService service) {
        this.service = service;
    }
    
    @GetMapping
    public Flux<Recommendation> getRecommendations(
        @RequestParam int productId) {

        return service.getByProductId(productId);
    }

    @Operation(summary = "Create a recommendation",
               description = "Create a recommendation for a specific product")
    @PostMapping
    public Mono<Recommendation> creatRecommendation(
        @RequestBody Recommendation recommendation) {
        
        return service.create(recommendation);
    }

    @DeleteMapping
    public Mono<Void> deleteRecommendations(
        @RequestParam int productId) {
        
        return service.deleteByProductId(productId);
    }
}
