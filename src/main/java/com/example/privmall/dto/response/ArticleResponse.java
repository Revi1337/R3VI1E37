package com.example.privmall.dto.response;

import com.example.privmall.dto.ArticleDto;

public record ArticleResponse(
        Long id,
        String title,
        String category,
        String content,
        String hashtag
) {

    public static ArticleResponse from(ArticleDto articleDto) {
        return new ArticleResponse(
                articleDto.id(), articleDto.title(), articleDto.category().getDescription(), articleDto.content(), articleDto.hashtag()
        );
    }

}
