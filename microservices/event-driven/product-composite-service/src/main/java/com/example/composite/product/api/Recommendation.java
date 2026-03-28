package com.example.composite.product.api;

public record Recommendation(
    int productId,
    int recommendationId,
    String author,
    int rating,
    String content
) {}