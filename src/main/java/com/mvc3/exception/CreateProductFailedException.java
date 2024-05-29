package com.mvc3.exception;

public class CreateProductFailedException extends RuntimeException {
    public CreateProductFailedException(String message) {
        super(message);
    }
}
