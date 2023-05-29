package com.example.privmall.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.example.privmall.dto.UserAccountDto;
import com.example.privmall.dto.request.principal.UserAccountPrincipal;
import com.example.privmall.exception.JwtSignatureException;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;

public class CustomValidationFilter extends BasicAuthenticationFilter {

    private static final String BEARER = "Bearer ";

    private final JwtService jwtService;

    private final UserAccountRepository userAccountRepository;

    public CustomValidationFilter(AuthenticationManager authenticationManager,
                                  JwtService jwtService,
                                  UserAccountRepository userAccountRepository) {
        super(authenticationManager);

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
        } catch (SignatureVerificationException exception) {
            throw new JwtSignatureException(); // TODO JWT 를 포함해서 Security 관련오류는 DispatcherServlet 을 지나 Controller 에 도착하기전에 익셉션을 던지기 때문에 @ControllerAdvice 에서 잡아줄 수 없음. 따라서 추 후 방법을 생각해내야 한다.
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
