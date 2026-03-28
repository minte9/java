package com.example.review.event;

public record ProductCreateEvent(
    int productId,
    String name,
    int weight
) {}
