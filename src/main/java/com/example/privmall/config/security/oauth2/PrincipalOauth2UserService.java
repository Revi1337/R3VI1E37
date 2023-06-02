package com.example.privmall.config.security.oauth2;


import com.example.privmall.domain.UserAccount;
import com.example.privmall.domain.enumerate.Host;
import com.example.privmall.domain.enumerate.RoleType;
import com.example.privmall.dto.request.principal.OAuth2UserInfoPrincipal;
import com.example.privmall.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    // 구글로부터 받은 userRequest 데이터가 후처리되는 함수
    // 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.

    private final UserAccountRepository userAccountRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        String clientId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = retrieveOAuth2UserInfo(clientId, attributes);

        String email = userRequest.getClientRegistration().getRegistrationId()
                + "_" + oAuth2UserInfo.getNickname() + "_" + oAuth2UserInfo.getId();
        if (! checkOAuth2UserDuplicate(email))
            forceJoinUserAccount(userRequest, clientId, oAuth2UserInfo);
        return new OAuth2UserInfoPrincipal(oAuth2UserInfo, attributes, Set.of(RoleType.USER.toString()));
    }

    protected OAuth2UserInfo retrieveOAuth2UserInfo(String clientId, Map<String, Object> attributes) {
        return switch (clientId) {
            case "google" -> new GoogleUserInfo(attributes);
            case "github" -> new GithubUserInfo(attributes);
            case "naver" -> new NaverUserInfo(attributes);
            case "facebook" -> new FacebookUserInfo(attributes);
            default -> throw new IllegalStateException("invalid clientId");         // TODO Need to Make Custom Exception
        };
    }

    protected boolean checkOAuth2UserDuplicate(String email) {
        return userAccountRepository.findByEmail(email).isPresent();
    }

    private void forceJoinUserAccount(OAuth2UserRequest userRequest, String clientId, OAuth2UserInfo oAuth2UserInfo) {
        UserAccount userAccount = UserAccount.create()
                .email(userRequest.getClientRegistration().getRegistrationId() + "_" + oAuth2UserInfo.getNickname() + "_" + oAuth2UserInfo.getId())
                .nickname(oAuth2UserInfo.getNickname())
                .password(passwordEncoder.encode(oAuth2UserInfo.getNickname() + oAuth2UserInfo.getId()))
                .host(Arrays.stream(Host.values())
                        .filter(host -> host.toString().equals(clientId.toUpperCase()))
                        .findFirst()
                        .orElseThrow(IllegalStateException::new))                   // TODO Need to Make Custom Exception
                .roles(RoleType.USER.toString())
                .build();
        userAccountRepository.save(userAccount);
    }
}
