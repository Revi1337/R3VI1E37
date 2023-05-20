package com.example.privmall.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank(message = "email must be specified") String email,
        @NotBlank(message = "password must be specified") String password
) {
    public static LoginRequest loginRequest(String email, String password) {
        return new LoginRequest(email, password);
    }
}
