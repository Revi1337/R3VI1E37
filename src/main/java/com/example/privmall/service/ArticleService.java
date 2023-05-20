package com.example.privmall.service;

import com.example.privmall.dto.UserAccountDto;
import com.example.privmall.dto.request.ArticleRegisterRequest;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.response.ArticleResponse;
import com.example.privmall.exception.UserNotFoundException;
import com.example.privmall.repository.ArticleRepository;
import com.example.privmall.repository.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service @RequiredArgsConstructor @Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    private final UserAccountRepository userAccountRepository;

    @Transactional
    public void createArticle(String email,
                              ArticleRegisterRequest articleRegisterRequest,
                              MultipartFile multipartFile) throws IOException {
        userAccountRepository
                .findByEmail(email)
                .map(UserAccountDto::from)
                .map(articleRegisterRequest::toDto)
                .orElseThrow(UserNotFoundException::new);

//        UserAccount userAccount = userAccountRepository
//                .findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("user not found"));
//        if (multipartFile != null) {
//            Attachment attachment = Attachment.create()
//                    .filename(multipartFile.getOriginalFilename())
//                    .fileType(multipartFile.getContentType())
//                    .data(multipartFile.getBytes())
//                    .build();
//            article.addAttachments(attachment);
//        }
//        articleRepository.save(article);
    }

    public List<ArticleResponse> searchAllArticle(SearchCondition searchCondition) {
        return articleRepository
                .searchAllArticle(searchCondition)
                .stream()
                .map(ArticleResponse::from)
                .toList();
    }

}
