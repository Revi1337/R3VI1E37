package com.example.privmall.dto.response;

import com.example.privmall.dto.UserAccountDto;

public record UserAccountResponse(
        Long id,
        String email,
        String nickname
) {

    public static UserAccountResponse of(Long id, String email, String nickname) {
        return new UserAccountResponse(
                id,
                email,
                nickname
        );
    }

    public static UserAccountResponse from(UserAccountDto userAccountDto) {
        return UserAccountResponse.of(
                userAccountDto.id(),
                userAccountDto.email(),
                userAccountDto.nickname()
        );
    }

}
