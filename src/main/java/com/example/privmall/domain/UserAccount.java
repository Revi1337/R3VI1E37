package com.example.privmall.domain;

import com.example.privmall.domain.enumerate.Host;
import com.example.privmall.domain.enumerate.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter @ToString
@Entity @Table(
        name = "USER_ACCOUNT",
        uniqueConstraints = {@UniqueConstraint(name = "email", columnNames = "EMAIL")})
public class UserAccount {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "EMAIL", nullable = false, length = 60)
    private String email;

    @Column(name = "NICKNAME", nullable = false, length = 15)
    private String nickname;

    @Column(name = "PASSWORD", length = 70)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "HOST", length = 15)
    private Host host;

    @Column(name = "ROLES", length = 20)
    private String roles;

    @ToString.Exclude
    @OneToMany(mappedBy = "userAccount")
    private final List<Article> articles = new ArrayList<>();

    public UserAccount changePassword(String encodedPassword) {
        this.password = encodedPassword;
        return this;
    }

    public UserAccount addAuthorities(RoleType role) {
        this.roles = this.roles + "," + role.toString();
        return this;
    }

    public List<String> getAuthorities() {
        if(this.roles.length() > 0)
            return Arrays.asList(this.roles.split(","));
        return new ArrayList<>();
    }

    protected UserAccount() {}

    @Builder(builderMethodName = "create")
    private UserAccount(Long id,
                        String email,
                        String nickname,
                        String password,
                        Host host,
                        String roles) {
        Assert.notNull(email, "email field must be specified");
        Assert.notNull(nickname, "nickname field must be specified");
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.host = host == null ? Host.GENERAL : host;
        this.roles = roles == null ? RoleType.USER.toString() : roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount that)) return false;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}