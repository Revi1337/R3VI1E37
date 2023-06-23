package com.example.privmall.dto.response;


import com.example.privmall.dto.request.principal.UserAccountPrincipal;

import java.util.Collection;

public record LoginResponse(String nickname,
                            Collection<String> roles) {

    public static LoginResponse of(String nickname, Collection<String> roles) {
        return new LoginResponse(
                nickname,
                roles
        );
    }

    public static LoginResponse from(UserAccountPrincipal userAccountPrincipal) {
        return LoginResponse.of(
                userAccountPrincipal.nickname(),
                userAccountPrincipal.roles().stream()
                        .map(role -> role.replace("ROLE_", ""))
                        .toList()
        );
    }
}
