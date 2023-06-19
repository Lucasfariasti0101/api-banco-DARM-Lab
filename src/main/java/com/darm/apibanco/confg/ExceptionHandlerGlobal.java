package com.darm.apibanco.confg;

import com.darm.apibanco.exception.BadRequestException;
import com.darm.apibanco.exception.ConflictException;
import com.darm.apibanco.exception.PersonNotFoundException;
import com.darm.apibanco.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ExceptionHandlerGlobal {

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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> badRequestExceptionHandler(BadRequestException ex, HttpServletRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                Instant.now(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetails);
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public ResponseEntity<ExceptionDetails> authenticationCredentialsNotFoundExceptionHandler(AuthenticationCredentialsNotFoundException ex,
                                                                                              HttpServletRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                Instant.now(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionDetails);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionDetails> conflictExceptionExceptionHandler(ConflictException ex,
                                                                                              HttpServletRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(),
                HttpStatus.CONFLICT.value(),
                Instant.now(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(exceptionDetails);
    }
}
