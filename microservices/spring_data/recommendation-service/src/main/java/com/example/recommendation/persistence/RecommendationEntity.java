package com.example.recommendation.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

@Document(collection = "recommendations")
public record RecommendationEntity (
    @Id String id,

    @Indexed
    int productId,  // index improves query performance

    int recommendationId,
    String author,
    int rating,
    String content
) {}
