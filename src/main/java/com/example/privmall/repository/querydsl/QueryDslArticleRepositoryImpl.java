package com.example.privmall.repository.querydsl;

import com.example.privmall.domain.Article;
import com.example.privmall.domain.QArticle;
import com.example.privmall.dto.request.SearchCondition;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class QueryDslArticleRepositoryImpl implements QueryDslArticleRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Article> searchAllArticle(SearchCondition searchCondition) {
        return jpaQueryFactory
                .selectFrom(QArticle.article)
                .offset(searchCondition.getOffset())
                .limit(searchCondition.size())
                .fetch();
    }

}
