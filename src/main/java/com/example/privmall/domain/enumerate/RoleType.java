package com.example.privmall.domain.enumerate;

import lombok.Getter;

@Getter
public enum RoleType  {
    USER("ROLE_USER"),
    MANAGER("ROLE_MANAGER"),
    ADMIN("ROLE_ADMIN");

    private final String name;

    RoleType(String name) {
        this.name = name;
    }
}
