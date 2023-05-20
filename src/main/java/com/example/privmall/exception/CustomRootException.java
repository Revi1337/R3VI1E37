package com.example.privmall.exception;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class CustomRootException extends RuntimeException {

    private final Map<String, String> validation = new HashMap<>();

    public CustomRootException(String message) { super(message); }

    abstract public String getStatusCode();

    public void addValidation(String fieldName, String message) {
        this.validation.put(fieldName, message);
    }

}
