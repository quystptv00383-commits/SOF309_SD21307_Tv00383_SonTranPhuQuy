package com.poly.sof309.controllers;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}