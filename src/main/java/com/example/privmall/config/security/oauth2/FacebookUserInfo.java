package com.example.privmall.config.security.oauth2;

import java.util.Map;

public class FacebookUserInfo implements OAuth2UserInfo {

    private final Map<String, Object> attributes;

    public FacebookUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
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
