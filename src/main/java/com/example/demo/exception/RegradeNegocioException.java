package com.example.demo.exception;

public class RegradeNegocioException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public RegradeNegocioException(String message) {
        super(message);
    }
}

