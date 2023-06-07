package com.example.privmall.domain;

import com.example.privmall.domain.enumerate.Category;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Getter @ToString(callSuper = true)
@Entity @Table(name = "ARTICLE")
public class Article extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ARTICLE_ID")
    private Long id;

    @Column(name = "TITLE", nullable = false, length = 100)
    private String title;

    @Lob
    @Column(name = "CONTENT", nullable = false)
    private String content;

    @Enumerated(value = EnumType.STRING)
    private Category category;

    @Column(name = "HASHTAG", length = 100)
    private String hashtag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private UserAccount userAccount;

    @ToString.Exclude
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final List<Comment> comments = new ArrayList<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final List<Attachment> attachments = new ArrayList<>();

    public void addComments(Comment... comments) {
        for (Comment comment : comments) {
            this.comments.add(comment);
            comment.setArticle(this);
        }
    }

    public void removeComments(Comment... comments) {
        for (Comment comment : comments) { this.comments.remove(comment); }
    }

    public void addAttachments(Attachment... attachments) {
        for (Attachment attachment : attachments) {
            this.attachments.add(attachment);
            attachment.setArticle(this);
        }
    }

    public void removeAttachments(Attachment... attachments) {
        for (Attachment attachment : attachments) { this.comments.remove(attachment); }
    }

    public List<String> getHashTagList() {
        if (this.hashtag.length() > 0)
            return Arrays.asList(this.hashtag.split(","));
        return new ArrayList<>();
    }

    protected Article() {}

    @Builder(builderMethodName = "create")
    private Article(UserAccount userAccount,
                    String title,
                    String content,
                    Category category,
                    String hashtag) {
        Assert.notNull(userAccount, "userAccount field must be specified");
        Assert.notNull(title, "title field must be specified");
        Assert.notNull(category, "category field must be specified");
        Assert.notNull(content, "content field must be specified");
        this.userAccount = userAccount;
        this.title = title;
        this.content = content;
        this.category = category;
        this.hashtag = hashtag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

}
