package com.example.privmall.dto.request;

import com.example.privmall.domain.enumerate.Category;
import com.example.privmall.dto.ArticleDto;
import com.example.privmall.dto.UserAccountDto;
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
                        .orElseThrow(IllegalArgumentException::new),    // TODO 일치하지 않는 카테고리가 없다는 Exception 을 만들어서 던져주어야 한다.
                hashTag
         );
    }

}
