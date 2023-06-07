package com.example.privmall.domain.enumerate;

import lombok.Getter;

@Getter
public enum Category {
    DEV("dev"),
    CTF("ctf"),
    WRITEUP("writeup"),
    CS("cs"),
    CHEETSHEET("cheet");

    private final String description;

    Category(String description) {
        this.description = description;
    }
}
