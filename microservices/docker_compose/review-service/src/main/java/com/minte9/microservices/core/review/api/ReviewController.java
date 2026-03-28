package com.minte9.microservices.core.review.api;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    
    @GetMapping
    public List<Review> getReviews(@RequestParam int productId) {

        return List.of(
            new Review(
                productId, 
                1, 
                "Alice", 
                "Great!", 
                "Really liked this product"
            ),
            new Review(
                productId,
                2,
                "Bob",
                "Good",
                "Does the job well"
            )
        );
    }
}
