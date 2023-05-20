package com.example.privmall.exception;

public class JwtSignatureException extends RuntimeException {

    public JwtSignatureException() {
        super("invalid token signature");
    }
}
