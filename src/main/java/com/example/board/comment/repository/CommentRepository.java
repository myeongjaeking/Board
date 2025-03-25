package com.example.board.comment.repository;

import com.example.board.comment.entity.Comment;
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

  public Comment findByBoardIdAndId(Long boardId, Long id) {
    return commentJpaRepository.findByBoardIdAndId(boardId, id);
  }

  public void delete(Long boardId, Long id) {
    Comment comment = findByBoardIdAndId(boardId,id);

    commentJpaRepository.delete(comment);
  }

}
