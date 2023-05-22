package com.example.privmall.config.security.oauth2;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@Component @Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 구글로부터 받은 userRequest 데이터가 후처리되는 함수
        // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("oAuth2User: " + oAuth2User);
        return oAuth2User;
    }
}
