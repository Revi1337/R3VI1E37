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
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
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

    @PostMapping(path = "/api/post/image")        // TODO 인증된 사용자만 요청할수있도록 변경 필요
    public ResponseEntity<String> uploadPostImage(@RequestParam(name = "file") MultipartFile multipartFile) throws IOException {
        String storedFileName = articleService.savePostImage(multipartFile);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(storedFileName);
    }

    @GetMapping("/api/post/image/{filename}")
    public ResponseEntity<Resource> showUploadedFile(@PathVariable(name = "filename") String filename) throws  MalformedURLException {
        FileSystemResource resource = new FileSystemResource(
                "D:\\SpringProject\\uploaddir\\" + filename + ".png");
        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_PNG)
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

//    @GetMapping(path = "/api/post/image/{image_uuid}")
//    public ResponseEntity<Resource> requestPostImage(@PathVariable(name = "image_uuid") String uuid) {
//        byte[] fileBinaryData = articleService.requestPostImage(uuid);
//        return ResponseEntity.ok()
//                .contentType(MediaType.IMAGE_PNG)
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        "attachment; filename=\"" + uuid + ".png")
//                .body(new ByteArrayResource(fileBinaryData));
//    }

}
