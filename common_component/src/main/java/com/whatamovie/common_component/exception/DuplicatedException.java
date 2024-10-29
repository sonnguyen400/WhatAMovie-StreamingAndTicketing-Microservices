package com.whatamovie.common_component.exception;

public class DuplicatedException extends RuntimeException {
    public DuplicatedException(String message) {
        super(message);
    }
}
