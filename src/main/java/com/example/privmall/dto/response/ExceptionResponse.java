package com.example.privmall.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Map;

//@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
public record ExceptionResponse(
        String code,
        String message,
        Map<String, String> validation
) {

    public ExceptionResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation != null ? validation : Map.of();
    }

    public static ExceptionResponse of(String code, String message, Map<String, String> validations) {
        return new ExceptionResponse(code, message, validations);
    }

    public static ExceptionResponse of(String code, String message) {
        return new ExceptionResponse(code, message, null);
    }

}
