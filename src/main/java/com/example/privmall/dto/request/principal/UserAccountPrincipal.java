package com.example.privmall.dto.request.principal;

import com.example.privmall.domain.enumerate.Host;
import com.example.privmall.dto.UserAccountDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

public record UserAccountPrincipal(
    Long id,
    String email,
    String nickname,
    String password,
    String phone,
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

    public static UserAccountPrincipal of(Long id, String email, String nickname, String password, String phone, Set<String> roles) {
        return new UserAccountPrincipal(
                id,
                email,
                nickname,
                password,
                phone,
                roles
        );
    }

    public static UserAccountPrincipal from(UserAccountDto userAccountDto) {
        List<String> roles = userAccountDto.toEntity().getAuthorities().stream().map(s -> "ROLE_" + s).toList();
        return UserAccountPrincipal.of(
                userAccountDto.id(),
                userAccountDto.email(),
                userAccountDto.nickname(),
                userAccountDto.password(),
                userAccountDto.phone(),
                new HashSet<>(roles)
        );
    }

    public UserAccountDto toDto() {
        return UserAccountDto.of(
                id,
                email,
                nickname,
                password,
                phone,
                Host.GENERAL,
                Arrays.toString(this.roles.toArray(String[]::new))
        );
    }

}
