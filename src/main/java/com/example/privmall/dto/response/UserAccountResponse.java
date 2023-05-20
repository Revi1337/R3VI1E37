package com.example.privmall.dto.response;

import com.example.privmall.domain.enumerate.Gender;
import com.example.privmall.dto.UserAccountDto;

public record UserAccountResponse(
        Long id,
        String email,
        String nickname,
        Gender gender
) {

    public static UserAccountResponse of(Long id, String email, String nickname, Gender gender) {
        return new UserAccountResponse(
                id,
                email,
                nickname,
                gender
        );
    }

    public static UserAccountResponse from(UserAccountDto userAccountDto) {
        return UserAccountResponse.of(
                userAccountDto.id(),
                userAccountDto.email(),
                userAccountDto.nickname(),
                userAccountDto.gender()
        );
    }

}
