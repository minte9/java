package com.example.review.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ReviewRepository
    extends MongoRepository<ReviewEntity, String> {

    List<ReviewEntity> findByProductId(int productId);

    void deleteByProductId(int productId);
}