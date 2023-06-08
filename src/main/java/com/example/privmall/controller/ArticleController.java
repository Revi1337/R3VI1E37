package com.example.privmall.controller;

import com.example.privmall.dto.request.ArticleRegisterRequest;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.request.SearchTypeCondition;
import com.example.privmall.dto.request.principal.UserAccountPrincipal;
import com.example.privmall.dto.response.ArticleResponse;
import com.example.privmall.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController @RequiredArgsConstructor @Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping(path = "/api/posts")
    public ResponseEntity<List<ArticleResponse>> searchAllArticle(
            SearchTypeCondition searchTypeCondition,
            @Valid SearchCondition searchCondition
    ) {
        List<ArticleResponse> articleResponses = articleService.searchAllArticle(searchTypeCondition, searchCondition)
                .stream().map(ArticleResponse::from).toList();
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleResponses);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(path = "/api/posts")
    public void createArticle(
            @AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal,
            @RequestBody @Valid ArticleRegisterRequest articleRegisterRequest) throws IOException {
        articleService.createArticle(
                articleRegisterRequest.toDto(userAccountPrincipal.toDto())
        );
        log.info("Principal : {} , Request : {}", userAccountPrincipal, articleRegisterRequest);
    }

    @GetMapping(path = "/api/post/{id}")
    public ResponseEntity<ArticleResponse> searchById(@PathVariable(name = "id") Long id) {
        ArticleResponse articleResponse = ArticleResponse.from(articleService.searchById(id));
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleResponse);
    }

}
