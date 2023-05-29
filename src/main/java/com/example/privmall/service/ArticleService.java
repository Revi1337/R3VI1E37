package com.example.privmall.service;

import com.example.privmall.domain.Article;
import com.example.privmall.domain.UserAccount;
import com.example.privmall.dto.ArticleDto;
import com.example.privmall.dto.request.ArticleRegisterRequest;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.request.principal.UserAccountPrincipal;
import com.example.privmall.dto.response.ArticleResponse;
import com.example.privmall.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service @RequiredArgsConstructor @Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Transactional
    public void createArticle(UserAccountPrincipal userAccountPrincipal,
                              ArticleRegisterRequest articleRegisterRequest) {
        UserAccount userAccount = userAccountPrincipal.toDto().toEntity();
        ArticleDto articleDto = ArticleDto.from(articleRegisterRequest);
        Article article = articleDto.toEntity(userAccount);
        articleRepository.save(article);
    }

    public List<ArticleResponse> searchAllArticle(SearchCondition searchCondition) {
        return articleRepository
                .searchAllArticle(searchCondition)
                .stream()
                .map(ArticleResponse::from)
                .toList();
    }

}
