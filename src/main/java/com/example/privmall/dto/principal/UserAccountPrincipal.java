package com.example.privmall.dto.principal;

import com.example.privmall.domain.enumerate.Gender;
import com.example.privmall.dto.UserAccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public record UserAccountPrincipal(
    String email,
    String nickname,
    String password,
    String phone,
    Gender gender,
    Set<String> roles
) implements UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(() -> role));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserAccountPrincipal of(String email, String nickname, String password, String phone, Gender gender, Set<String> roles) {
        return new UserAccountPrincipal(
                email,
                nickname,
                password,
                phone,
                gender,
                roles
        );
    }

    public static UserAccountPrincipal from(UserAccountDto userAccountDto) {
        List<String> roles = userAccountDto.toEntity().getAuthorities().stream().map(s -> "ROLE_" + s).toList();
        return UserAccountPrincipal.of(
                userAccountDto.email(),
                userAccountDto.nickname(),
                userAccountDto.password(),
                userAccountDto.phone(),
                userAccountDto.gender(),
                new HashSet<>(roles)
        );
    }

}