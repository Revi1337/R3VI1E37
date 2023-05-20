package com.example.privmall.repository;

import com.example.privmall.domain.Article;
import com.example.privmall.repository.querydsl.QueryDslArticleRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends
        JpaRepository<Article, Long>, QueryDslArticleRepository {

}
