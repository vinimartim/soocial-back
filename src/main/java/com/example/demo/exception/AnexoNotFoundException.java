package com.example.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.*;

@ResponseStatus(NOT_FOUND)
public class AnexoNotFoundException extends RuntimeException {
    public AnexoNotFoundException(String message) {
        super(message);
    }

    public AnexoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}