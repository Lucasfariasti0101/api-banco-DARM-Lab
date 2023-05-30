package com.darm.apibanco.confg;

import com.darm.apibanco.exeption.PersonNotFoundException;
import com.darm.apibanco.exeption.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class exceptionHandlerGlobal {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDetails> resourceNotFoundExceptionHandler(ResourceNotFoundException ex, HttpServletRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                Instant.now(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDetails);
    }

    @ExceptionHandler(PersonNotFoundException.class)
    public ResponseEntity<ExceptionDetails> personNotFoundExceptionHandler(PersonNotFoundException ex, HttpServletRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                Instant.now(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionDetails);
    }
}