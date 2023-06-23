//package com.example.privmall.repository;
//
//import com.example.privmall.domain.Article;
//import com.example.privmall.domain.Comment;
//import com.example.privmall.domain.UserAccount;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//
//@SpringBootTest
//class CreateEntityTest {
//
//    private final UserAccountRepository userAccountRepository;
//
//    private final ArticleRepository articleRepository;
//
//    private final CommentRepository commentRepository;
//
//    @Autowired
//    public CreateEntityTest(UserAccountRepository userAccountRepository,
//                            ArticleRepository articleRepository,
//                            CommentRepository commentRepository) {
//        this.userAccountRepository = userAccountRepository;
//        this.articleRepository = articleRepository;
//        this.commentRepository = commentRepository;
//    }
//
//    @BeforeEach
//    public void initialize() {
//        userAccountRepository.deleteAll();
//        articleRepository.deleteAll();
//        commentRepository.deleteAll();
//    }
//
//    @Test
//    @DisplayName(value = "Entity 테스트 - 댓글조회")
//    public void createUserAccountTest() {
//        UserAccount userAccount1 = UserAccount.create().email("test1@test").nickname("test1").phone("asd1").build();
//        UserAccount userAccount2 = UserAccount.create().email("test2@test").nickname("test2").phone("asd2").build();
//        userAccountRepository.save(userAccount1);
//        userAccountRepository.save(userAccount2);
//
//        Comment comment1 = Comment.create().content("comment content1").build();
//        Comment comment2 = Comment.create().content("comment content2").build();
//        Comment comment3 = Comment.create().content("comment content3").build();
//        Comment comment4 = Comment.create().content("comment content4").build();
//
//        Article article1 = Article.create().userAccount(userAccount1).title("title").content("content").build();
//        article1.addComments(comment1, comment2);
//        Article article2 = Article.create().userAccount(userAccount2).title("title").content("content").build();
//        article2.addComments(comment3, comment4);
//
//        articleRepository.save(article1);
//        articleRepository.save(article2);
//
//        List<Comment> commentByInnerJoinArticle = commentRepository.findCommentByInnerJoinArticle();
//        for (Comment comment : commentByInnerJoinArticle) {
//            System.out.println("comment.getArticle() = " + comment.getArticle());
//        }
//    }
//
//    @Test
//    @DisplayName(value = "Entity 테스트 - 댓글조회 --> fetch join")
//    public void create() {
//        UserAccount userAccount1 = UserAccount.create().email("test1@test").nickname("test1").phone("asd1").build();
//        UserAccount userAccount2 = UserAccount.create().email("test2@test").nickname("test2").phone("asd2").build();
//        userAccountRepository.save(userAccount1);
//        userAccountRepository.save(userAccount2);
//
//        Comment comment1 = Comment.create().content("comment content1").build();
//        Comment comment2 = Comment.create().content("comment content2").build();
//        Comment comment3 = Comment.create().content("comment content3").build();
//        Comment comment4 = Comment.create().content("comment content4").build();
//
//        Article article1 = Article.create().userAccount(userAccount1).title("title").content("content").build();
//        article1.addComments(comment1, comment2);
//        Article article2 = Article.create().userAccount(userAccount2).title("title").content("content").build();
//        article2.addComments(comment3, comment4);
//
//        articleRepository.save(article1);
//        articleRepository.save(article2);
//
//        List<Comment> commentByInnerJoinArticle = commentRepository.findCommentByInnerJoinFetchArticle();
//        for (Comment comment : commentByInnerJoinArticle) {
//            System.out.println("comment.getArticle() = " + comment.getArticle());
//        }
//    }
//
//    @Test
//    @DisplayName(value = "게시물을 삭제하면 관련된 댓글도 모두 삭제되어야 한다.")
//    public void removeCommentWhenDeleteArticle() {
//        UserAccount userAccount1 = UserAccount.create().email("test1@test").nickname("test1").phone("asd1").build();
//        userAccountRepository.save(userAccount1);
//
//        Article article1 = Article.create().userAccount(userAccount1).title("title").content("content").build();
//        Comment comment1 = Comment.create().content("comment content1").build();
//        Comment comment2 = Comment.create().content("comment content2").build();
//        article1.addComments(comment1, comment2);
//        articleRepository.save(article1);
//
//        List<Comment> findComment = commentRepository.findAll();
//        for (Comment comment : findComment) { System.out.println("comment = " + comment); }
//
//        articleRepository.delete(article1);
//        List<Comment> deleteComment = commentRepository.findAll();
//        for (Comment comment : deleteComment) {
//            System.out.println("comment = " + comment);
//        }
//    }
//
//    @Test
//    @DisplayName(value = "게시물이 삭제되지않고 댓글만 삭제할 수 있어야 한다.")
//    public void removeCommentNonDeleteArticle() {
//        UserAccount userAccount1 = UserAccount.create().email("test1@test").nickname("test1").phone("asd1").build();
//        userAccountRepository.save(userAccount1);
//
//        Article article1 = Article.create().userAccount(userAccount1).title("title").content("content").build();
//        Comment comment1 = Comment.create().content("comment content1").build();
//        Comment comment2 = Comment.create().content("comment content2").build();
//        article1.addComments(comment1, comment2);
//        articleRepository.save(article1);
//
//        Article findArticle = articleRepository.findById(article1.getId()).get();
//        findArticle.removeComments(comment2);
//    }
//
//    @Test
//    @DisplayName(value = "해시태그가 정상적으로 파싱되어야한다.")
//    public void hashTagMustBeParseCorrectly() {
//        UserAccount userAccount1 = UserAccount.create()
//                .email("test1@test")
//                .nickname("test1")
//                .phone("asd1")
//                .build();
//        userAccountRepository.save(userAccount1);
//
//        Article article1 = Article.create()
//                .userAccount(userAccount1)
//                .title("title")
//                .content("content")
//                .hashtag("a,b,c,d")
//                .build();
//        articleRepository.save(article1);
//
//        Article findArticle = articleRepository.findById(article1.getId()).orElseThrow(RuntimeException::new);
//        assertThat(findArticle.getHashTagList()).isEqualTo(List.of("a","b","c","d"));
//    }
//
//}
