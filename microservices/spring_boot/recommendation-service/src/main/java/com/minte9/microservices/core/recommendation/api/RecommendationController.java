package com.minte9.microservices.core.recommendation.api;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/recommendation")
public class RecommendationController {
    
    @GetMapping
    public List<Recommendation> getRecommendations(@RequestParam int productId) {

        return List.of(
            new Recommendation(
                productId, 
                1, 
                "Carol",
                5,
                "Higly recommended"
            ),
            new Recommendation(
                productId, 
                2, 
                "Dave",
                4,
                "Worth buying"
            )
        );
    }
}
