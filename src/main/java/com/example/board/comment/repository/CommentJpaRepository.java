package com.example.board.comment.repository;

import com.example.board.comment.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByBoardId(Long boardId);
  Comment findByBoardIdAndId(Long boardId, Long id);
}
