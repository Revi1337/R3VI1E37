package com.example.privmall.exception;

public class PrivilegeException extends CustomRootException{

    public PrivilegeException(String message) {
        super(message);
    }

    @Override
    public String getStatusCode() {
        return "401";
    }
}
