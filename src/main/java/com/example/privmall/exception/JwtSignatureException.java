package com.example.privmall.exception;

public class JwtSignatureException extends CustomRootException {

    public JwtSignatureException() {
        super("invalid token signature");
    }
    @Override
    public String getStatusCode() {
        return "400";
    }
}
