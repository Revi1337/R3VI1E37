package com.example.privmall.service;

import com.example.privmall.domain.Article;
import com.example.privmall.dto.ArticleDto;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.request.SearchTypeCondition;
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
    public void createArticle(ArticleDto articleDto) {
        Article article = articleDto.toEntity(articleDto.userAccountDto().toEntity());
        articleRepository.save(article);
    }

    public List<ArticleDto> searchAllArticle(SearchTypeCondition searchTypeCondition, SearchCondition searchCondition) {
        return articleRepository
                .searchAllArticle(searchTypeCondition, searchCondition)
                .stream()
                .map(ArticleDto::from)
                .toList();
    }

    public ArticleDto searchById(Long id) {
        return articleRepository.findById(id)
                .map(ArticleDto::from)
                .orElseThrow(IllegalStateException::new);               // TODO 커스텀익셉션 박아주어야한다.
    }

}
