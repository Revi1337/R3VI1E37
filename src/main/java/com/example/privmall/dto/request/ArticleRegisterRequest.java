package com.example.privmall.dto.request;

import com.example.privmall.domain.enumerate.Category;
import com.example.privmall.dto.ArticleDto;
import com.example.privmall.dto.UserAccountDto;
import com.example.privmall.exception.NonExistsCategoryException;
import jakarta.validation.constraints.NotBlank;

import java.util.Arrays;


public record ArticleRegisterRequest(
        @NotBlank(message = "title must be specified") String title,
        @NotBlank(message = "message must be specified") String content,
        @NotBlank(message = "category must be specified") String category,
        String hashTag
) {

    public ArticleDto toDto(UserAccountDto userAccountDto) {
        return ArticleDto.of(
                null,
                userAccountDto,
                title,
                content,
                Arrays.stream(Category.values())
                        .filter(preCategory -> preCategory.getDescription().equalsIgnoreCase(category))
                        .findFirst()
                        .orElseThrow(() -> new NonExistsCategoryException("specified category non exists")),
                hashTag,
                null,
                null
         );
    }

}
