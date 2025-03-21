package com.example.board.comment.repository;

import com.example.board.comment.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

  List<Comment> findCommentByBoardId(Long boardId);
  Comment findCommentByBoardIdAndId(Long boardId, Long id);
}
