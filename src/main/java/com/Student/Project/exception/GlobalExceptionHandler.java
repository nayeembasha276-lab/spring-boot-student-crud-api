package com.Student.Project.exception;

import org.springframework.dao.DataIntegrityViolationException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    //Student Not Found
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse>handleStudentNotFound(StudentNotFoundException ex, HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder().
                timestamp(LocalDateTime.now()).
                status(HttpStatus.NOT_FOUND.value()).
                error("Student Not found").
                message(ex.getMessage()).
                path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    //Duplicate Roll Number
    @ExceptionHandler(DuplicateRollNoException.class)
    public ResponseEntity<ErrorResponse>handleDuplicateRollNumber(DuplicateRollNoException dr ,HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder().
                timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Duplicate Roll Number ")
                .message(dr.getMessage())
                .path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    //Duplicate Email
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse>handleDuplicateEmail(DuplicateEmailException de,HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Duplicate Email")
                .message(de.getMessage())
                .path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    //Validation Errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>>handleValiddationError(MethodArgumentNotValidException ex){
        Map<String,String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors.put(error.getField(),error.getDefaultMessage()));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex ,HttpServletRequest request){
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Duplicate Data")
                .message("Email Already exists")
                .path(request.getRequestURI()).build();
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);

    }

}
