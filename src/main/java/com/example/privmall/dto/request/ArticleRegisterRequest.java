package com.example.privmall.dto.request;

import com.example.privmall.dto.ArticleDto;
import com.example.privmall.dto.UserAccountDto;
import jakarta.validation.constraints.NotBlank;


public record ArticleRegisterRequest(
        @NotBlank(message = "title must be specified") String title,
        @NotBlank(message = "message must be specified") String content,
        String hashTag
) {

    public ArticleDto toDto(UserAccountDto userAccountDto) {
         return ArticleDto.of(
                 userAccountDto,
                 title,
                 content,
                 hashTag
         );
    }

}
