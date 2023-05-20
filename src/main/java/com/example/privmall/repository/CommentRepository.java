package com.example.privmall.repository;

import com.example.privmall.domain.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"article"})
    @Query(value = "select c from Comment as c")
    List<Comment> findCommentByInnerJoinFetchArticle();

    @Query(value = "select c from Comment as c inner join c.article")
    List<Comment> findCommentByInnerJoinArticle();
}
