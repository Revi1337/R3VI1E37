package com.example.privmall.domain;

import com.example.privmall.domain.enumerate.Gender;
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

    @Column(name = "EMAIL", nullable = false, length = 40)
    private String email;

    @Column(name = "NICKNAME", nullable = false, length = 15)
    private String nickname;

    @Column(name = "PASSWORD", length = 70)
    private String password;

    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "GENDER", nullable = false, length = 10)
    private Gender gender;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "HOST", length = 15)
    private Host host;

    @Column(name = "ROLES", length = 20)
    private String roles;

    @ToString.Exclude
    @OneToMany(mappedBy = "userAccount")
    private final List<Article> articles = new ArrayList<>();

//    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "TOKEN_ID")
//    public Token token;
//
//    public UserAccount addInfo(String password, Token token, RoleType... roleType) {
//        this.password = password;
//        this.token = token;
//        if (roleType == null) {
//            this.roles = this.roles + "";
//        } else {
//            for (RoleType type : roleType) {
//                this.roles = this.roles + "," + type.toString();
//            }
//        }
//        return this;
//    }

    public UserAccount changePassword(String encodedPassword) {
        this.password = encodedPassword;
        return this;
    }

//    public void addToken(Token token) {
//        this.token = token;
//        token.setUserAccount(this);
//    }

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
    private UserAccount(String email,
                        String nickname,
                        String password,
                        String phone,
                        Gender gender,
                        Host host,
                        String roles) {
        Assert.notNull(email, "email field must be specified");
        Assert.notNull(nickname, "nickname field must be specified");
        Assert.notNull(phone, "phone field must be specified");
        Assert.notNull(gender, "gender field must be specified");
        this.email = email;
        this.nickname = nickname;
        this.password = password;
        this.phone = phone;
        this.gender = gender;
        this.host = host;
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