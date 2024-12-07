package com.sgv.clinica.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestCita extends RuntimeException {
    public BadRequestCita(String message) {
        super(message);
    }
}
