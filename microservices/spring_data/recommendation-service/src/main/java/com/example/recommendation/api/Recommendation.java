package com.example.recommendation.api;

public record Recommendation (
    int productId,
    int recommendationId,
    String author,
    int rating,
    String content
) {}
