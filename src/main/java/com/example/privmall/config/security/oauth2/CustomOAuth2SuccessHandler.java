package com.example.privmall.config.security.oauth2;

import com.example.privmall.config.security.JwtService;
import com.example.privmall.dto.request.principal.OAuth2UserInfoPrincipal;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;
import java.util.Map;


@RequiredArgsConstructor
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        System.out.println("authentication = " + authentication);
        OAuth2UserInfoPrincipal principal = (OAuth2UserInfoPrincipal) authentication.getPrincipal();
        Map<String, Object> attributes = principal.getAttributes();
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + jwtService.generate(principal.getName()));
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
