package com.example.privmall.exception;

import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.core.AuthenticationException;

public class CustomJwtException extends AuthenticationException {

    public CustomJwtException(JWTVerificationException jwtVerificationException) {
        super(jwtVerificationException.getMessage());
    }

}
