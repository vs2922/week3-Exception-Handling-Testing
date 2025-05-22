package com.pharmainventory.inventory.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.validation.FieldError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse("NOT_FOUND", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<ErrorField> details = ex.getBindingResult().getFieldErrors().stream()
            .map(f -> new ErrorField(f.getField(), f.getRejectedValue(), f.getDefaultMessage()))
            .collect(Collectors.toList());
        ErrorResponse error = new ErrorResponse("VALIDATION_ERROR", "Validation failed", details);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler({ ConstraintViolationException.class, MethodArgumentTypeMismatchException.class })
    public ResponseEntity<ErrorResponse> handleBadRequest(Exception ex) {
        ErrorResponse error = new ErrorResponse("BAD_REQUEST", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}

// Supporting DTOs
class ErrorResponse {
    private String errorCode;
    private String message;
    private List<ErrorField> details;
    public ErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode; this.message = message;
    }
    public ErrorResponse(String errorCode, String message, List<ErrorField> details) {
        this.errorCode = errorCode; this.message = message; this.details = details;
    }
    // getters and setters omitted for brevity
}

class ErrorField {
    private String field;
    private Object rejectedValue;
    private String message;
    public ErrorField(String field, Object rejectedValue, String message) {
        this.field = field; this.rejectedValue = rejectedValue; this.message = message;
    }
    // getters and setters omitted
}