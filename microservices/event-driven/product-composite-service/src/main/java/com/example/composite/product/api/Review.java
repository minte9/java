package com.example.composite.product.api;

public record Review(
    int productId,
    int reviewId,
    String author,
    String subject,
    String content
) {}