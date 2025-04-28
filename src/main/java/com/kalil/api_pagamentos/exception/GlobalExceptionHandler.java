package com.kalil.api_pagamentos.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.validation.ConstraintViolationException;

@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(wrapErrors(fieldErrors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, Map<String, String>>> handleConstraintViolationException(ConstraintViolationException ex) {
        Map<String, String> fieldErrors = new HashMap<>();

        ex.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String errorMessage = violation.getMessage();
            fieldErrors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(wrapErrors(fieldErrors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Map<String, String>>> handleRuntimeException(RuntimeException ex) {
        Map<String, String> fieldErrors = new HashMap<>();
        fieldErrors.put("error", ex.getMessage() != null ? ex.getMessage() : "Unexpected error occurred");

        return new ResponseEntity<>(wrapErrors(fieldErrors), HttpStatus.BAD_REQUEST);
    }

    private Map<String, Map<String, String>> wrapErrors(Map<String, String> errors) {
        Map<String, Map<String, String>> wrapper = new HashMap<>();
        wrapper.put("errors", errors);
        return wrapper;
    }
}
