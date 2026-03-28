package com.example.review.api;

import com.example.review.persistence.ReviewEntity;
import com.example.review.persistence.ReviewRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/review")
public class ReviewController {
    
    private final ReviewRepository repository;

    public ReviewController(ReviewRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Review> getReviews(@RequestParam int productId) {

        List<ReviewEntity> entities = repository.findByProductId(productId);

        return entities.stream()
                .map(e -> new Review(
                    e.productId(), 
                    e.reviewId(),
                    e.author(),
                    e.subject(),
                    e.content()
                ))
                .toList();
    }

    @PostMapping
    public Review createReview(@RequestBody Review review) {

        ReviewEntity entity = new ReviewEntity(
            null, // let Mongo generate _id
            review.productId(),
            review.reviewId(),
            review.author(),
            review.subject(),
            review.content()
        );

        repository.save(entity);

        return review;
    }

    @DeleteMapping
    public void deleteReviews(@RequestParam int productId) {
        repository.deleteByProductId(productId);
    }
}
