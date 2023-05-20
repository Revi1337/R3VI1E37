package com.example.privmall.repository.querydsl;

import com.example.privmall.domain.Article;
import com.example.privmall.dto.request.SearchCondition;

import java.util.List;

public interface QueryDslArticleRepository {

    List<Article> searchAllArticle(SearchCondition searchCondition);

}
