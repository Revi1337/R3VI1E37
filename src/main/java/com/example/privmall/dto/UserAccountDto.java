package com.example.privmall.dto;

import com.example.privmall.domain.UserAccount;
import com.example.privmall.domain.enumerate.Gender;
import com.example.privmall.domain.enumerate.Host;


public record UserAccountDto(
        Long id,
        String email,
        String nickname,
        String password,
        String phone,
        Gender gender,
        Host host,
        String roles
) {
    public static UserAccountDto of(Long id, String email, String nickname, String password, String phone, Gender gender, Host host, String roles) {
        return new UserAccountDto(id, email, nickname, password, phone, gender, host, roles);
    }

    public static UserAccountDto from(UserAccount userAccount) {
        return UserAccountDto.of(
                userAccount.getId(),
                userAccount.getEmail(),
                userAccount.getNickname(),
                userAccount.getPassword(),
                userAccount.getPhone(),
                userAccount.getGender(),
                userAccount.getHost(),
                userAccount.getRoles()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.create()
                .email(email)
                .nickname(nickname)
                .password(password)
                .phone(phone)
                .gender(gender)
                .host(host)
                .roles(roles)
                .build();
    }

}
