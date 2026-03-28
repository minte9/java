package com.example.review.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
public record ReviewEntity (
    @Id String id,

    int productId,
    int reviewId,
    String author,
    String subject,
    String content
) {}