package com.example.privmall.dto.request;

import com.example.privmall.domain.enumerate.Host;
import com.example.privmall.dto.UserAccountDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record JoinRequest(
        @NotBlank(message = "email must be specified") @Email(message = "must be email format") String email,
        @NotBlank(message = "nickname must be specified") String nickname,
        String password,
        @NotBlank(message = "phone must be specified") String phone,
        Host host
) {
    public static JoinRequest of(String email, String nickname, String password, String phone, Host host) {
        return new JoinRequest(email, nickname, password, phone, host);
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                null,
                email,
                nickname,
                password,
                phone,
                host,
                null
        );
    }

//    public UserAccount toEntity() {
//        return UserAccount.create()
//                .email(email)
//                .nickname(nickname)
//                .password(password)
//                .phone(phone)
//                .gender(gender)
//                .host(host)
//                .build();
//    }

}
