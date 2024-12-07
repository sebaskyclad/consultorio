package com.sgv.clinica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestCita.class)
    public ResponseEntity<Object> handleBadRequestCita(BadRequestCita error) {
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.BAD_REQUEST.value(), error.getMessage()), HttpStatus.BAD_REQUEST
        );
    }
}
