package com.minte9.microservices.core.composite.product.api;

public record Product(
    int productId,
    String name,
    int weight
) {}