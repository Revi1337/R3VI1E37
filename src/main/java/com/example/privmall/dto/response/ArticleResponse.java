package com.example.privmall.dto.response;

import com.example.privmall.dto.ArticleDto;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String title,
        String category,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy
) {

    public static ArticleResponse from(ArticleDto articleDto) {
        return new ArticleResponse(
                articleDto.id(),
                articleDto.title(),
                articleDto.category().getDescription(),
                articleDto.content(),
                articleDto.hashtag(),
                articleDto.createdAt(),
                articleDto.createdBy()
        );
    }

}
