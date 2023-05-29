package com.example.privmall.controller;

import com.example.privmall.dto.request.ArticleRegisterRequest;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.request.principal.UserAccountPrincipal;
import com.example.privmall.dto.response.ArticleResponse;
import com.example.privmall.exception.PrivilegeException;
import com.example.privmall.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController @RequiredArgsConstructor @Slf4j
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping(path = "/api/posts")
    public ResponseEntity<List<ArticleResponse>> searchAllArticle(
            @Valid SearchCondition searchCondition) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(articleService.searchAllArticle(searchCondition));
    }

    @PostMapping(path = "/api/posts")
    public void createArticle(
            @AuthenticationPrincipal UserAccountPrincipal userAccountPrincipal,
            @RequestBody ArticleRegisterRequest articleRegisterRequest) throws IOException {
        if (userAccountPrincipal == null)
            throw new PrivilegeException("this endpoint must be called by authenticated user");
        articleService.createArticle(userAccountPrincipal, articleRegisterRequest);
        log.info("Principal : {} , Request : {}", userAccountPrincipal, articleRegisterRequest);
    }

//    @PostMapping(path = "/api/posts")
//    public void createArticle(
//            @RequestHeader(value = "Authorization") String email,
//            @RequestPart(value = "request") ArticleRegisterRequest articleRegisterRequest,
//            @RequestPart(value = "file", required = false) MultipartFile multipartFile) throws IOException {
//        articleService.createArticle(email, articleRegisterRequest, multipartFile);
//        log.info("request : {}, file : {}", articleRegisterRequest, multipartFile);
//    }

}
