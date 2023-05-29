package com.example.privmall.dto;

import com.example.privmall.domain.UserAccount;
import com.example.privmall.domain.enumerate.Host;


public record UserAccountDto(
        Long id,
        String email,
        String nickname,
        String password,
        Host host,
        String roles
) {
    public static UserAccountDto of(Long id, String email, String nickname, String password, Host host, String roles) {
        return new UserAccountDto(id, email, nickname, password, host, roles);
    }

    public static UserAccountDto from(UserAccount userAccount) {
        return UserAccountDto.of(
                userAccount.getId(),
                userAccount.getEmail(),
                userAccount.getNickname(),
                userAccount.getPassword(),
                userAccount.getHost(),
                userAccount.getRoles()
        );
    }

    public UserAccount toEntity() {
        return UserAccount.create()
                .id(id)
                .email(email)
                .nickname(nickname)
                .password(password)
                .host(host)
                .roles(roles)
                .build();
    }

}
