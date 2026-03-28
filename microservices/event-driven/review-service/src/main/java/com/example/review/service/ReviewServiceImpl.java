package com.example.review.service;

//import java.time.Duration;
import org.springframework.stereotype.Service;
import com.example.review.api.Review;
import com.example.review.persistence.ReviewEntity;
import com.example.review.persistence.ReviewRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;

    public ReviewServiceImpl(ReviewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Review> create(Review review) {

        ReviewEntity entity = new ReviewEntity(
                null, // let Mongo generate _id
                review.productId(),
                review.reviewId(),
                review.author(),
                review.subject(),
                review.content()
        );

        return repository.save(entity)
                .map(e -> new Review(
                        e.productId(),
                        e.reviewId(),
                        e.author(),
                        e.subject(),
                        e.content()
                ));
    }

    @Override
    public Flux<Review> getByProductId(int productId) {

        return repository.findByProductId(productId)

                //.delayElements((Duration.ofSeconds(2)))   // temporary: to prove reactivity
                //.delayElements(Duration.ofMillis(100))      // temporary: for the stress test

                .map(e -> new Review(
                        e.productId(),
                        e.reviewId(),
                        e.author(),
                        e.subject(),
                        e.content()
                ));
    }

    @Override
    public Mono<Void> deleteByProductId(int productId) {
        return repository.deleteByProductId(productId);
    }
}
