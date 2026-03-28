package com.minte9.microservices.core.product.api;

public record Product (
    int productId,
    String name,
    int weight
) {}
