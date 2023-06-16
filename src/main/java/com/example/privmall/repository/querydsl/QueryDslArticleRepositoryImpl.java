package com.example.privmall.repository.querydsl;

import com.example.privmall.domain.Article;
import com.example.privmall.domain.enumerate.Category;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.request.SearchTypeCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.example.privmall.domain.QArticle.*;

@RequiredArgsConstructor
public class QueryDslArticleRepositoryImpl implements QueryDslArticleRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Article> searchAllArticle(SearchTypeCondition searchTypeCondition, SearchCondition searchCondition) {
        return jpaQueryFactory
                .selectFrom(article)
                .where(
                        categoryEqIgnoreCase(searchTypeCondition.category()),
                        titleEqIgnoreCase(searchTypeCondition.title()),
                        createdByEq(searchTypeCondition.createdBy()),
                        hashtagEqIgnoreCase(searchTypeCondition.hashtag()),
                        titleContainsIgnoreCase(searchTypeCondition.title_contains()),
                        contentContainsIgnoreCase(searchTypeCondition.content_contains()),
                        hashtagContainsIgnoreCase(searchTypeCondition.hashtag_contains())
                )
                .offset(searchCondition.getOffset())
                .limit(searchCondition.size())
                .fetch();
    }

    private BooleanExpression categoryEqIgnoreCase(String category) {
        return category != null ? article.category.eq(Category.valueOf(category.toUpperCase())) : null;
    }

    private BooleanExpression titleEqIgnoreCase(String title) {
        return StringUtils.hasText(title) ? article.title.equalsIgnoreCase(title) : null;
    }

    private BooleanExpression createdByEq(String createdBy) {
        return StringUtils.hasText(createdBy) ? article.createdBy.eq(createdBy) : null;
    }

    private BooleanExpression hashtagEqIgnoreCase(String hashtag) {
        return StringUtils.hasText(hashtag) ? article.hashtag.equalsIgnoreCase(hashtag) : null;
    }

    private BooleanExpression titleContainsIgnoreCase(String title) {
        return StringUtils.hasText(title) ? article.title.containsIgnoreCase(title) : null;
    }

    private BooleanExpression contentContainsIgnoreCase(String content) {
        return StringUtils.hasText(content) ? article.content.containsIgnoreCase(content) : null;
    }

    private BooleanExpression hashtagContainsIgnoreCase(String hashtag) {
        if (!StringUtils.hasText(hashtag))
            return null;
        return article.hashtag.likeIgnoreCase(hashtag + ",%" )
                .or(article.hashtag.likeIgnoreCase("%," + hashtag + ",%"))
                .or(article.hashtag.likeIgnoreCase("%," + hashtag))
                .or(hashtagEqIgnoreCase(hashtag));
    }

}
