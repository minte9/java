package com.minte9.presentation.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    /**
     * Handles validation errors triggered by @Valid.
     * 
     * Example:
     *   - Missing polls in POST /presentation
     * 
     * Returns:
     *   - 400 Bad Request
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Invalid request body"));
    }

    /**
     * Handles explicit API errors thrown from controller/service
     * 
     * Examples:
     *   - 404 Not Found when presentation does not exist
     *   - 409 Conflict when no poll is currently displayed
     */
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseStatusException(
        ResponseStatusException exception
    ) {
        return ResponseEntity
                .status(exception.getStatusCode())
                .body(Map.of("error", exception.getReason() == null
                        ? "Request failed"
                        : exception.getReason()));
    }
}
