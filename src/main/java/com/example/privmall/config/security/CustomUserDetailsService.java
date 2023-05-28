package com.example.privmall.config.security;

import com.example.privmall.dto.UserAccountDto;
import com.example.privmall.dto.request.principal.UserAccountPrincipal;
import com.example.privmall.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component @RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userAccountRepository.findByEmail(email)
                .map(UserAccountDto::from)
                .map(UserAccountPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("user not found"));
    }
}
