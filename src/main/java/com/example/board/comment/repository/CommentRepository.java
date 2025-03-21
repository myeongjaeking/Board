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

  public List<Comment> findCommentByBoardId(Long boardId) {
    return commentJpaRepository.findCommentByBoardId(boardId);
  }

  public Comment findCommentByBoardIdAndId(Long boardId, Long id){
    return commentJpaRepository.findCommentByBoardIdAndId(boardId,id);
  }

}
