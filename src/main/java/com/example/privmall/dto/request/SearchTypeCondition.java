package com.example.privmall.dto.request;


import com.example.privmall.domain.enumerate.Category;
import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;

public record SearchTypeCondition(
        @NotBlank(message = "title must be specified") String title,
        String category,
        String content,
        String hashtag,
        String createdAt,
        String createdBy,
        String title_contains,
        String content_contains
) {

    public SearchTypeCondition(String title,
                               String category,
                               String content,
                               String hashtag,
                               String createdAt,
                               String createdBy,
                               String title_contains,
                               String content_contains) {
        if (!isValidParameter(title, category, content, hashtag, createdAt, createdBy, title_contains, content_contains))
            throw new IllegalStateException("invalid category");        // TODO Custom Exception 을 박아주어야 한다.
        this.title = title;
        this.category = category;
        this.content = content;
        this.hashtag = hashtag;
        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.title_contains = title_contains;
        this.content_contains = content_contains;
    }

    public boolean isValidParameter(String title,
                                    String category,
                                    String content,
                                    String hashtag,
                                    String createdAt,
                                    String createdBy,
                                    String title_contains,
                                    String content_contains) {
        if (category == null)
            return true;
        return Arrays.stream(Category.values())
                .anyMatch(value -> value.getDescription().equalsIgnoreCase(category));
    }
}
