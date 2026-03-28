package com.example.product.api;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Product entity")
public record Product (
        @Schema(description = "Product ID")
        int productId,

        String name,
        int weight
) {}


