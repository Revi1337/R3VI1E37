package com.example.privmall.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter @ToString
@Entity @Table(name = "TOKEN")
public class Token {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TOKEN_ID")
    public Long id;

    @Column(name = "ACCESS_TOKEN", nullable = false)
    public String accessToken;

    @Column(name = "REFRESH_TOKEN")
    public String refreshToken;

    @Setter(AccessLevel.PACKAGE)
    @ToString.Exclude
    @OneToOne(mappedBy = "token", fetch = FetchType.LAZY)
    private UserAccount userAccount;

    protected Token() {}

    public Token(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

}
