package com.example.board.comment.service;

import static com.example.board.global.exception.GlobalExceptionCode.NOT_FOUND_COMMENT;

import com.example.board.comment.entity.Comment;
import com.example.board.comment.repository.CommentJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

  private final CommentJpaRepository commentJpaRepository;

  public void save(Comment comment) {
    commentJpaRepository.save(comment);
  }

  public List<Comment> findByBoardId(Long boardId) {
    return commentJpaRepository.findByBoardId(boardId);
  }

  public Comment findByBoardIdAndId(Long boardId, Long commentId, String nickname) {
    Comment comment = commentJpaRepository.findByBoardIdAndId(boardId, commentId)
        .orElseThrow(NOT_FOUND_COMMENT::newException);

    comment.validateAccess(nickname);

    return comment;
  }

  public void delete(Long boardId, Long commentId, String nickname) {
    Comment comment = findByBoardIdAndId(boardId, commentId, nickname);

    commentJpaRepository.delete(comment);
  }

}