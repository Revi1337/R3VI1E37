package com.example.privmall.config.security;

import com.example.privmall.dto.response.ExceptionResponse;
import com.example.privmall.exception.CustomJwtException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static org.springframework.http.HttpStatus.*;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private ExceptionResponse exceptionResponse;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        System.out.println("CustomAuthenticationEntryPoint.commence" + authException.getClass().getSimpleName());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        if (authException.getClass().equals(CustomJwtException.class)) {
            response.setStatus(UNAUTHORIZED.value());
            exceptionResponse = ExceptionResponse.of(
                    String.valueOf(UNAUTHORIZED.value()),
                    authException.getMessage()
            );
        } else if (authException.getClass().equals(InsufficientAuthenticationException.class)) {
            response.setStatus(UNAUTHORIZED.value());
            exceptionResponse = ExceptionResponse.of(
                    String.valueOf(UNAUTHORIZED.value()),
                    "this endpoint must be called by authenticated user"
            );
        }

        String unAuthorizeResponse = objectMapper.writeValueAsString(exceptionResponse);
        response.getWriter().println(unAuthorizeResponse);
    }
}
