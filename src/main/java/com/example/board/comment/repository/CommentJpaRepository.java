package com.example.board.comment.repository;

import com.example.board.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment,Long> {

}
