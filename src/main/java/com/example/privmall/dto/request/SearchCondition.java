package com.example.privmall.dto.request;

import jakarta.validation.constraints.PositiveOrZero;

public record SearchCondition(
        @PositiveOrZero(message = "size must be positive num") Long size,
        @PositiveOrZero(message = "page must be positive num") Long page
) {

    private static final int MAX_SIZE = 2000;

    public SearchCondition(Long size, Long page) {
        this.size = (size == null || size == 0) ? 10 : size;
        this.page = page == null ? 0 : page;
    }

    public Long getOffset() {
        return (Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
    }

}
