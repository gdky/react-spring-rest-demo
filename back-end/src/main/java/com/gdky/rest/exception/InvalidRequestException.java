package com.gdky.restfull.exception;

import org.springframework.validation.BindingResult;

public class InvalidRequestException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final BindingResult errors;

    public InvalidRequestException(BindingResult errors) {
        this.errors = errors;
    }

    public BindingResult getErrors() {
        return this.errors;
    }
}