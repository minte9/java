package com.example.review.api;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Review entity")
public record Review (
        @Schema(description = "Product ID")
        int productId,

        @Schema(description = "Review ID")
        int reviewId,

        String author,
        String subject,
        String content
) {}

