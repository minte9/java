package com.minte9.microservices.core.composite.product.api;

public record Recommendation(
    int productId,
    int recommendationId,
    String author,
    int rating,
    String content
) {}