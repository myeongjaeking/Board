package com.example.board.comment.repository;

import com.example.board.comment.entity.Comment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentJpaRepository extends JpaRepository<Comment, Long> {

  List<Comment> findByBoardId(Long boardId);
  Optional<Comment> findByBoardIdAndId(Long boardId, Long id);

}