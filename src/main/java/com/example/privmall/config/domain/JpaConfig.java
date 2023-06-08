package com.example.privmall.config.domain;

import com.example.privmall.config.security.oauth2.OAuth2UserInfo;
import com.example.privmall.dto.request.principal.OAuth2UserInfoPrincipal;
import com.example.privmall.dto.request.principal.UserAccountPrincipal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration @EnableJpaAuditing
public class JpaConfig {

    @Bean
    public AuditorAware<String>  auditorAware() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null )
                return Optional.of("testUser");
            if (authentication.getPrincipal().getClass().isAssignableFrom(UserAccountPrincipal.class))
                return Optional.of(
                        ((UserAccountPrincipal) authentication.getPrincipal()).toDto().toEntity().getNickname()
                );
            else if (authentication.getPrincipal().getClass().isAssignableFrom(OAuth2UserInfo.class))
                return Optional.of(
                        ((OAuth2UserInfo) authentication.getPrincipal()).getNickname()
                );
            else
                return Optional.of("");
        };


    }

}
