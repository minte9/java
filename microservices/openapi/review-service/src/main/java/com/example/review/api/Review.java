package com.example.review.api;

public record Review (
    int productId,
    int reviewId,
    String author, 
    String subject,
    String content
) {}
