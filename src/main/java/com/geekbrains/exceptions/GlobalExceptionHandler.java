package com.geekbrains.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<AppError> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(new AppError("RESOURCE_NOT_FOUND", ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}
