package com.minog.minog.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class MinogException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    public MinogException(String message) {
        super(message);
    }

    public MinogException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();

    public void addValidation(String fieldName, String message) {
        validation.put(fieldName, message);
    }
}
