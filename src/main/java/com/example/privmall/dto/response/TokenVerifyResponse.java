package com.example.privmall.dto.response;

public record TokenVerifyResponse(
        String userId,
        boolean result
) {
}
