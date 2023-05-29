package com.example.privmall.config.security.oauth2;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = (Map<String, Object>) attributes.get("response");
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("id"));
    }

    @Override
    public String getNickname() {
        return String.valueOf(attributes.get("name"));
    }
}
