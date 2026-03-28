package com.minte9.microservices.core.composite.product.api;

import java.util.List;

public record ProductAggreagate(
    int productId,
    String name,
    int weight,
    List<Review> reviews,
    List<Recommendation> recommendations    
) {}
