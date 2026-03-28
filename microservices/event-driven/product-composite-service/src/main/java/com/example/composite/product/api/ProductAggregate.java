package com.example.composite.product.api;

import java.util.List;

public record ProductAggregate(
    int productId,
    String name,
    int weight,
    List<Review> reviews,
    List<Recommendation> recommendations    
) {}
