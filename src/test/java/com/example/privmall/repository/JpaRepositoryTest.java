package com.example.privmall.repository;

import com.example.privmall.config.domain.JpaConfig;
import com.example.privmall.config.domain.QueryDslConfig;
import com.example.privmall.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@DisplayName("JPA 연결 테스트")
@Import({JpaConfig.class, QueryDslConfig.class})
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;

    @Autowired
    public JpaRepositoryTest(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @DisplayName("select 테스트")
    @Test
    public void givenTestData_whenSelecting_thenWorksFine() {
        // given

        // when
        List<Article> articles = articleRepository.findAll();

        // then
        assertThat(articles).isNotNull().hasSize(0);
    }

}