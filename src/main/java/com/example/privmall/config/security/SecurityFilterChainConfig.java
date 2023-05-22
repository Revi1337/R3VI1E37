package com.example.privmall.config.security;

import com.example.privmall.config.security.oauth2.PrincipalOauth2UserService;
import com.example.privmall.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;


@Configuration @RequiredArgsConstructor
@EnableWebSecurity @EnableMethodSecurity(securedEnabled = true)
public class SecurityFilterChainConfig {

    private final JwtService jwtService;

    private final UserAccountRepository userAccountRepository;

    private final CorsConfigurationSource corsConfigurationSource;

    private final PrincipalOauth2UserService principalOauth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
        return httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .apply(new CustomConfig(jwtService, userAccountRepository)).and()
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/**").permitAll())
                .oauth2Login(oauth -> oauth
                        .loginPage("/")
                        .userInfoEndpoint()
                        .userService(principalOauth2UserService))
                .build();
    }

    @RequiredArgsConstructor
    public static class CustomConfig extends AbstractHttpConfigurer<CustomConfig, HttpSecurity> {
        private final JwtService jwtService;
        private final UserAccountRepository userAccountRepository;
        @Override
        public void configure(HttpSecurity httpSecurity) {
            AuthenticationManager authenticationManager = httpSecurity.getSharedObject(AuthenticationManager.class);
            httpSecurity
                    .addFilter(new CustomAuthenticationFilter(authenticationManager, jwtService))
                    .addFilter(new CustomValidationFilter(authenticationManager, jwtService, userAccountRepository));
        }
    }

}
