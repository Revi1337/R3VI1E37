package com.example.privmall.dto.response;

import com.example.privmall.domain.Article;

public record ArticleResponse(
        Long id,
        String title,
        String content
) {
    public static ArticleResponse of(Long id, String title, String content ) {
        return new ArticleResponse(id, title, content);
    }

    public static ArticleResponse from(Article article) {
        return ArticleResponse.of(
                article.getId(), article.getTitle(), article.getContent()

        );
    }

}
