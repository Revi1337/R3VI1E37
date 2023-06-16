package com.example.privmall.dto.response;

import com.example.privmall.dto.ArticleDto;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public record ArticleResponse(
        Long id,
        String title,
        String category,
        String content,
        List<String> hashtag,
        LocalDateTime createdAt,
        String createdBy
) {

    public static ArticleResponse from(ArticleDto articleDto) {
        return new ArticleResponse(
                articleDto.id(),
                articleDto.title(),
                articleDto.category().getDescription(),
                articleDto.content(),
                articleDto.hashtag() == null ? List.of() : Arrays.stream(articleDto.hashtag().split(",")).toList(),
                articleDto.createdAt(),
                articleDto.createdBy()
        );
    }

}
