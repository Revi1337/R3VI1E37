package com.example.privmall.exception;

public class NonExistsCategoryException extends CustomRootException{

    public NonExistsCategoryException(String message) {
        super(message);
    }

    @Override
    public String getStatusCode() {
        return "400";
    }

}
