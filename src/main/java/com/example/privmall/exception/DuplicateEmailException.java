package com.example.privmall.exception;

public class DuplicateEmailException extends RuntimeException{

    public DuplicateEmailException(String email) {
        super("An account registered with " + email + " already exists");
    }
}
