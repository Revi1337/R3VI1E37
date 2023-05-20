package com.example.privmall.controller;

import com.example.privmall.dto.request.ArticleRegisterRequest;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.response.ArticleResponse;
import com.example.privmall.service.ArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.*;


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
            @RequestHeader(value = "Authorization") String email,                            // TODO 나중에 Spring Security 를 추가하고 나서는 Holder 에서 꺼내오는 로직으로 변경해야 한다.
            @RequestPart(value = "request") ArticleRegisterRequest articleRegisterRequest,
            @RequestPart(value = "file", required = false) MultipartFile multipartFile) throws IOException {
        articleService.createArticle(email, articleRegisterRequest, multipartFile);
        log.info("request : {}, file : {}", articleRegisterRequest, multipartFile);
    }

}
