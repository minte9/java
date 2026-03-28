package com.minte9.microservices.core.composite.product.api;

public record Review(
    int productId,
    int reviewId,
    String author,
    String subject,
    String content
) {}