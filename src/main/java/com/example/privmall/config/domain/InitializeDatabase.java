package com.example.privmall.config.domain;

import com.example.privmall.domain.Article;
import com.example.privmall.domain.UserAccount;
import com.example.privmall.domain.enumerate.Gender;
import com.example.privmall.repository.ArticleRepository;
import com.example.privmall.repository.UserAccountRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.IntStream;

@Profile(value = "local") @Component
public class InitializeDatabase {

    private final InitializeService initializeService;

    public InitializeDatabase(InitializeService initializeService) {
        System.out.println("InitializeDatabase.InitializeDatabase(3)");
        this.initializeService = initializeService;
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("InitializeDatabase.postConstruct(4)");
        initializeService.initialize();
    }

    @Component
    static class InitializeService {

        private final UserAccountRepository userAccountRepository;

        private final ArticleRepository articleRepository;

        private final PasswordEncoder passwordEncoder;

        public InitializeService(UserAccountRepository userAccountRepository,
                                 ArticleRepository articleRepository,
                                 PasswordEncoder passwordEncoder) {
            this.userAccountRepository = userAccountRepository;
            this.articleRepository = articleRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @PostConstruct
        public void postConstruct() {
            System.out.println("InitializeService.postConstruct(2)");
        }

        @Transactional
        public void initialize() {
            System.out.println("InitializeService.initialize(5)");
            IntStream.rangeClosed(1,4).forEach(value -> {
                UserAccount userAccount = UserAccount.create()
                        .email("test" + value + "@.com")
                        .password(passwordEncoder.encode("1234"))
                        .nickname("test" + value)
                        .phone("phoneNum" + value)
                        .gender(Gender.MALE)
                        .build();
                userAccountRepository.save(userAccount);
                Article article = Article.create()
                        .title("title" + value)
                        .content("content" + value)
                        .userAccount(userAccount)
                        .build();
                articleRepository.save(article);
            });
        }

    }
}
