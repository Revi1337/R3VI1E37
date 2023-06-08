package com.example.privmall.repository.querydsl;

import com.example.privmall.domain.Article;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.request.SearchTypeCondition;

import java.util.List;

public interface QueryDslArticleRepository {

//    List<Article> searchAllArticle(SearchCondition searchCondition);

    List<Article> searchAllArticle(SearchTypeCondition searchTypeCondition, SearchCondition searchCondition);

}
