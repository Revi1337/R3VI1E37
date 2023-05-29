package com.example.privmall.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.privmall.dto.UserAccountDto;
import com.example.privmall.dto.request.principal.UserAccountPrincipal;
import com.example.privmall.exception.CustomJwtException;
import com.example.privmall.repository.UserAccountRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class CustomValidationFilter extends BasicAuthenticationFilter {

    private static final String BEARER = "Bearer ";

    private final JwtService jwtService;

    private final UserAccountRepository userAccountRepository;

    public CustomValidationFilter(AuthenticationManager authenticationManager,
                                  JwtService jwtService,
                                  UserAccountRepository userAccountRepository,
                                  AuthenticationEntryPoint authenticationEntryPoint) {
        super(authenticationManager, authenticationEntryPoint);
        this.jwtService = jwtService;
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER)) {
            chain.doFilter(request, response);
            return;
        }
        String jwtToken = authorizationHeader.substring(7);
        String email;
        try {
            email = JWT
                    .require(Algorithm.HMAC512("DUMMY"))
                    .build()
                    .verify(jwtToken)
                    .getClaim("sub")
                    .asString();
        } catch (JWTVerificationException jwtVerificationException) {
            getAuthenticationEntryPoint().commence(request, response, new CustomJwtException(jwtVerificationException));
            return;
        }
        UserAccountPrincipal userAccountPrincipal = userAccountRepository.findByEmail(email)
                .map(UserAccountDto::from)
                .map(UserAccountPrincipal::from)
                .orElseThrow(() -> new UsernameNotFoundException("user cannot found"));
        UsernamePasswordAuthenticationToken authenticatedToken = UsernamePasswordAuthenticationToken
                .authenticated(userAccountPrincipal, null, userAccountPrincipal.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticatedToken);
        chain.doFilter(request, response);
    }
}
