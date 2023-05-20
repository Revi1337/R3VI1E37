package com.example.privmall.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter @ToString
@Entity @Table(name = "COMMENT")
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "COMMENT_ID")
    private Long id;

    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Setter(value = AccessLevel.PACKAGE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICLE_ID")
    private Article article;

    protected Comment() {}

    @Builder(builderMethodName = "create")
    private Comment(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Comment comment)) return false;
        return id != null && Objects.equals(id, comment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
