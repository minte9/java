package com.example.review.api;

import org.springframework.web.bind.annotation.*;
import com.example.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/review")
public class ReviewController {

    public final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<Review> getReviews(
            @RequestParam int productId) {

        return service.getByProductId(productId);
    }

    @Operation(summary = "Create a review",
            description = "Create a review for a specific product")
    @PostMapping
    public Mono<Review> creatReview(
            @RequestBody Review review) {

        return service.create(review);
    }

    @DeleteMapping
    public Mono<Void> deleteReviews(
            @RequestParam int productId) {

        return service.deleteByProductId(productId);
    }
}
