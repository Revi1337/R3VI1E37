package com.example.privmall.config.security;

import com.example.privmall.domain.UserAccount;
import com.example.privmall.dto.UserAccountDto;
import com.example.privmall.dto.request.principal.UserAccountPrincipal;
import com.example.privmall.dto.request.LoginRequest;
import com.example.privmall.dto.response.UserAccountResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;


public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final JwtService jwtUtil;

    public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JwtService jwtUtil) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        setPostOnly(true);
        setFilterProcessesUrl("/api/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginRequest.class);
            UsernamePasswordAuthenticationToken unauthenticatedToken = UsernamePasswordAuthenticationToken
                    .unauthenticated(loginRequest.email(), loginRequest.password());
            return getAuthenticationManager().authenticate(unauthenticatedToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        System.out.println("CustomAuthenticationFilter.successfulAuthentication");
        UserAccountPrincipal principal = (UserAccountPrincipal) authResult.getPrincipal();
        System.out.println(principal.nickname());
        String userResponse = objectMapper.writeValueAsString(
                UserAccountResponse.from(principal.toDto())
        );
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtUtil.generate(principal.getUsername()));
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        PrintWriter writer = response.getWriter();
        writer.println(userResponse);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        System.out.println("CustomAuthenticationFilter.unsuccessfulAuthentication");
        String message = failed.getMessage();
    }

}
