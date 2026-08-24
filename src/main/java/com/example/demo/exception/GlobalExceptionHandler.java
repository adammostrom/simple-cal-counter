package com.example.demo.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/*
Controller
    ↓
Service
    ↓
Repository
    ↓
Optional.empty()
    ↓
throw ResourceNotFoundException
    ↓
GlobalExceptionHandler
    ↓
404 JSON response

*/
@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {

    private static final Logger log =
        LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex){
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(404, ex.getMessage(), LocalDateTime.now()));
    }
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleUnexpected(Exception ex){
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(500, "Internal server error", LocalDateTime.now()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidatioNFailed(ValidationException ex){
        log.error(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(400, ex.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleBadJson(HttpMessageNotReadableException ex) {
        log.error(ex.getMessage(), ex);
        return ResponseEntity.badRequest().body(Map.of("status", 400,"message", "Invalid request data"));
    }

    public ResponseEntity<?> handleDatabaseError(CannotGetJdbcConnectionException ex) {
    log.error("Database connection failed", ex);

    return ResponseEntity
            .status(HttpStatus.SERVICE_UNAVAILABLE)
            .body(Map.of("error", "Nutrition service temporarily unavailable"));
    }
}
