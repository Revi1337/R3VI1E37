package com.example.privmall.dto;

import com.example.privmall.domain.Article;
import com.example.privmall.domain.UserAccount;
import com.example.privmall.dto.request.ArticleRegisterRequest;

public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag
) {
    public static ArticleDto of(Long id, UserAccountDto userAccountDto, String title, String content, String hashtag) {
        return new ArticleDto(id, userAccountDto, title, content, hashtag);
    }

    public static ArticleDto of(UserAccountDto userAccountDto, String title, String content, String hashtag) {
        return new ArticleDto(null, userAccountDto, title, content, hashtag);
    }

    public static ArticleDto from(ArticleRegisterRequest articleRegisterRequest) {
        return ArticleDto.of(
                null,
                null,
                articleRegisterRequest.title(),
                articleRegisterRequest.content(),
                articleRegisterRequest.hashTag()
        );
    }

    public Article toEntity(UserAccount userAccount) {
        return Article.create()
                .userAccount(userAccount)
                .title(title)
                .content(content)
                .hashtag(hashtag)
                .build();
    }

}
