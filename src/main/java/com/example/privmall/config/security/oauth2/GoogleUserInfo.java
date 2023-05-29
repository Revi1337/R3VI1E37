package com.example.privmall.config.security.oauth2;


import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{

    private final Map<String, Object> attributes;

    public GoogleUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getId() {
        return String.valueOf(attributes.get("sub"));
    }

    @Override
    public String getNickname() {
        return String.valueOf(attributes.get("name"));
    }

}
