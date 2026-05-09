package com.minte9.presentation.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(PresentationNotFoundException.class)
    public ResponseEntity<Void> handlePresentationNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(NoCurrentPollException.class)
    public ResponseEntity<Void> handleNoCurrentPoll() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    @ExceptionHandler(NoMorePollException.class)
    public ResponseEntity<Void> handNoMorePolls() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
