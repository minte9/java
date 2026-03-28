package com.example.product.event;

public record ProductCreateEvent(
    int productId,
    String name,
    int weight
) {}