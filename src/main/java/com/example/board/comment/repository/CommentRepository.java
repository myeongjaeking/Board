package com.example.board.comment.repository;

import com.example.board.comment.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

  private final CommentJpaRepository commentJpaRepository;

  public void save(Comment comment) {
    commentJpaRepository.save(comment);
  }

}
