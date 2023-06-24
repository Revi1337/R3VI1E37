package com.example.privmall.controller;


import com.example.privmall.WithAuthUser;
import com.example.privmall.dto.request.ArticleRegisterRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(controllers = {ArticleController.class}, excludeAutoConfiguration = SecurityAutoConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc @Transactional
public class ArticleControllerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final MockMvc mockMvc;

    public ArticleControllerTest(@Autowired MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @DisplayName("[API] 모든 게시글 조회")
    @Test
    public void fetchAllPostsTest() throws Exception {
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @DisplayName("[API] 특정 게시글 조회")
    @Test
    public void fetchSpecifyPostsTest() throws Exception {
        mockMvc.perform(get("/api/post/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.category").value("cs"))
                .andExpect(jsonPath("$.id").value(1L))
                .andDo(print());
    }

    @DisplayName("[API] 인증되지 않은 사용자가 게시글을 업로드하면 UnAuthorize 를 뱉는다.")
    @Test
    public void unAuthorizeWhenNonAuthenticatedUserUploadArticle() throws Exception {
        ArticleRegisterRequest articleRegisterRequest =
                new ArticleRegisterRequest("title1", "content1", "DEV", "Spring");
        String articleRegisterRequestJson = objectMapper.writeValueAsString(articleRegisterRequest);

        mockMvc.perform(post("/api/posts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(articleRegisterRequestJson))
                .andExpect(status().isUnauthorized())
                .andDo(print());
    }

    @WithAuthUser(email = "revi1337@naver.com", roles = {"ROLE_USER", "ROLE_ADMIN"})
    @DisplayName("[API] 인증된 사용자가 게시글 업로드하면 200 코드를 뱉는다.")
    @Test
    public void isOkWhenAuthenticatedUserUploadArticle() throws Exception {
        ArticleRegisterRequest articleRegisterRequest =
                new ArticleRegisterRequest("title1", "content1", "DEV", "Spring");
        String articleRegisterRequestJson = objectMapper.writeValueAsString(articleRegisterRequest);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(articleRegisterRequestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @WithAuthUser(email = "revi1337@naver.com", roles = {"ROLE_USER", "ROLE_ADMIN"})
    @DisplayName("[API] 인증된 사용자가 게시글 업로드할때 title 이 존재하지않으면 message 를 포함한 400 을 뱉는다.")
    @Test
    public void isBadRequestWhenAuthenticatedUserUploadArticleButMissingTitle() throws Exception {
        ArticleRegisterRequest articleRegisterRequest =
                new ArticleRegisterRequest(null, "content", "DEV", "Spring");
        String articleRegisterRequestJson = objectMapper.writeValueAsString(articleRegisterRequest);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(articleRegisterRequestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validation.title").value("title must be specified"))
                .andDo(print());
    }

    @WithAuthUser(email = "revi1337@naver.com", roles = {"ROLE_USER", "ROLE_ADMIN"})
    @DisplayName("[API] 인증된 사용자가 게시글 업로드할때 content 가 존재하지않으면 message 를 포함한 400 을 뱉는다.")
    @Test
    public void isBadRequestWhenAuthenticatedUserUploadArticleButMissingContent() throws Exception {
        ArticleRegisterRequest articleRegisterRequest =
                new ArticleRegisterRequest("title", null, "DEV", "Spring");
        String articleRegisterRequestJson = objectMapper.writeValueAsString(articleRegisterRequest);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(articleRegisterRequestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validation.content").value("message must be specified"))
                .andDo(print());
    }

    @WithAuthUser(email = "revi1337@naver.com", roles = {"ROLE_USER", "ROLE_ADMIN"})
    @DisplayName("[API] 인증된 사용자가 게시글 업로드할때 category 가 존재하지않으면 message 를 포함한 400 을 뱉는다.")
    @Test
    public void isBadRequestWhenAuthenticatedUserUploadArticleButMissingCategory() throws Exception {
        ArticleRegisterRequest articleRegisterRequest =
                new ArticleRegisterRequest("title", "content", null, "Spring");
        String articleRegisterRequestJson = objectMapper.writeValueAsString(articleRegisterRequest);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(articleRegisterRequestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.validation.category").value("category must be specified"))
                .andDo(print());
    }

    @WithAuthUser(email = "revi1337@naver.com", roles = {"ROLE_USER", "ROLE_ADMIN"})
    @DisplayName("[API] 인증된 사용자가 게시글 업로드할때 hashtag 는 존재하지 않아도 200 을 뱉어야한다.")
    @Test
    public void isOkWhenAuthenticatedUserUploadArticleWithMissingHashtag() throws Exception {
        ArticleRegisterRequest articleRegisterRequest =
                new ArticleRegisterRequest("title", "content", "DEV", null);
        String articleRegisterRequestJson = objectMapper.writeValueAsString(articleRegisterRequest);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(articleRegisterRequestJson))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @WithAuthUser(email = "revi1337@naver.com", roles = {"ROLE_USER", "ROLE_ADMIN"})
    @DisplayName("[API] 인증된 사용자가 게시글 업로드할때 존재하지 않는 Category 에 업로드하면 익셉션을 뱉어야 한다.")
    @Test
    public void isExceptionWhenAuthenticatedUserUploadArticleButSpecifyNonExistsCategory() throws Exception {
        ArticleRegisterRequest articleRegisterRequest =
                new ArticleRegisterRequest("title", "content", "DummyCategory", null);
        String articleRegisterRequestJson = objectMapper.writeValueAsString(articleRegisterRequest);

        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(articleRegisterRequestJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("specified category non exists"))
                .andDo(print());
    }

}
