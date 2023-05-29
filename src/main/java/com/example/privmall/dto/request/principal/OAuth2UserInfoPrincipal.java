package com.example.privmall.dto.request.principal;

import com.example.privmall.config.security.oauth2.OAuth2UserInfo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public record OAuth2UserInfoPrincipal(
        OAuth2UserInfo oAuth2UserInfo,
        Map<String, Object> attributes,
        Set<String> roles
) implements OAuth2User {

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> authorities.add(() -> role));
        return authorities;
    }

    @Override
    public String getName() {
        return oAuth2UserInfo.getId();
    }
}
