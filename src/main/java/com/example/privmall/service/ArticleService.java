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
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@Service @RequiredArgsConstructor @Transactional(readOnly = true)
public class ArticleService {

    private final ArticleRepository articleRepository;

    private static final String UPLOAD_DIR = "D:\\SpringProject\\uploaddir";

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

    public String savePostImage(MultipartFile multipartFile) throws IOException { // TODO Must Be Add OWASP File Upload Secure Coding
        String originalFileName = StringUtils.cleanPath(
                Objects.requireNonNull(multipartFile.getOriginalFilename())
        );
        if (originalFileName.contains("..") || originalFileName.contains("/") || originalFileName.contains("\\"))
            throw new IllegalStateException("malicious character detected");
        File file = new File(UPLOAD_DIR, UUID.randomUUID().toString().concat(".png"));
        FileCopyUtils.copy(multipartFile.getBytes(), file);
        return file.getName();
    }

//    public byte[] requestPostImage(String uuid) {
//        File file = new File(UPLOAD_DIR, String.format("%s.%s", uuid, "png"));
//        byte[] fileBinaryData = null;
//        try (FileInputStream fileInputStream = new FileInputStream(file)) {
//            fileBinaryData = fileInputStream.readAllBytes();
//        } catch (Exception e) {
//            e.printStackTrace();                        // TODO Exception 처리해주어야 함.
//        }
//        return fileBinaryData;
//    }
}
