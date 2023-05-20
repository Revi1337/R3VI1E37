package com.example.privmall.exception;

public class UserNotFoundException extends CustomRootException {

    private static final String MSG = "user not found";

    public UserNotFoundException() {
        super(MSG);
    }

    public UserNotFoundException(String fieldName, String message) {
        super(MSG);
        addValidation(fieldName, message);
    }

    @Override
    public String getStatusCode() { return "400"; }

}
