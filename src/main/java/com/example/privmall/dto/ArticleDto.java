package com.example.privmall.dto;

import com.example.privmall.domain.Article;
import com.example.privmall.domain.UserAccount;
import com.example.privmall.domain.enumerate.Category;


public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        Category category,
        String hashtag
) {
    public static ArticleDto of(Long id,
                                UserAccountDto userAccountDto,
                                String title,
                                String content,
                                Category category,
                                String hashtag) {
        return new ArticleDto(
                id, userAccountDto, title, content, category, hashtag
        );
    }

    public static ArticleDto from(Article article) {
        return ArticleDto.of(
                article.getId(),
                UserAccountDto.from(article.getUserAccount()),
                article.getTitle(),
                article.getContent(),
                article.getCategory(),
                article.getHashtag()
        );
    }

    public Article toEntity(UserAccount userAccount) {
        return Article.create()
                .userAccount(userAccount)
                .title(title)
                .content(content)
                .category(category)
                .hashtag(hashtag)
                .build();
    }

}
