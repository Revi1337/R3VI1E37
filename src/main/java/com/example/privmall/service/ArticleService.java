package com.example.privmall.service;

import com.example.privmall.domain.Article;
import com.example.privmall.dto.ArticleDto;
import com.example.privmall.dto.request.SearchCondition;
import com.example.privmall.dto.request.SearchTypeCondition;
import com.example.privmall.exception.NonExistsArticleException;
import com.example.privmall.repository.ArticleRepository;
import com.example.privmall.utils.DefenseFileUploadUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;
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
                .orElseThrow(() -> new NonExistsArticleException("cannot found article"));
    }

    public String savePostImage(MultipartFile multipartFile) throws IOException { // TODO 시큐어 코딩 완료. 추가해야할 것은 파일이 저장되는서버를 구분시키는 로직만 추가시키면 됨.
        String requestedContentType = multipartFile.getContentType();
        String originalFileName = DefenseFileUploadUtils.cleanFileName(multipartFile.getOriginalFilename());

        if (!DefenseFileUploadUtils.checkPngFileWithMagicBytes(multipartFile))
            throw new FileUploadException("only png allowed to upload");
        if (DefenseFileUploadUtils.hasContainMaliciousCharacter(originalFileName))
            throw new FileUploadException("malicious character detected");
        if (!DefenseFileUploadUtils.matchesFileExtensions(originalFileName, "png"))
            throw new FileUploadException("only png allowed to allowed");
        if (DefenseFileUploadUtils.hasMultipleExtensions(originalFileName))
            throw new FileUploadException("multiple extensions detected");
        if (!(requestedContentType.split("/")[1].equals("png") && DefenseFileUploadUtils.checkPngFileWithMagicBytes(multipartFile) &&
                DefenseFileUploadUtils.matchesFileExtensions(originalFileName, "png")))
            throw new FileUploadException("content_type & file_header & file_extension must be same");

        File file = new File(UPLOAD_DIR, UUID.randomUUID().toString().concat(".png"));
        FileCopyUtils.copy(multipartFile.getBytes(), file);
        return file.getName();
    }

}
