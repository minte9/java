package com.example.recommendation.api;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Recommendation entity")
public record Recommendation (
    @Schema(description = "Product ID")
    int productId,

    @Schema(description = "Recommendation ID")
    int recommendationId,

    String author,
    int rating,
    String content
) {}
