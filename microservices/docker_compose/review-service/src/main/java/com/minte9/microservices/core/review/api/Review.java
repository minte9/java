package com.minte9.microservices.core.review.api;

public record Review (
    int productId,
    int reviewId,
    String author, 
    String subject,
    String content
) {}
