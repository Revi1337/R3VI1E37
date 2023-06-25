package com.example.privmall.exception;

public class NonExistsArticleException extends CustomRootException{

    public NonExistsArticleException(String message) {
        super(message);
    }

    @Override
    public String getStatusCode() {
        return "404";
    }
}
