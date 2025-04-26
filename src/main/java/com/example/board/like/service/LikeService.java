package com.example.board.like.service;

import com.example.board.board.entity.Board;
import com.example.board.board.service.BoardRepository;
import com.example.board.global.common.SecurityUtil;
import com.example.board.like.entity.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

  private final LikeRepository likeRepository;
  private final BoardRepository boardRepository;

  @Transactional
  public void doLike(String nickname, Long boardId) {
    Board board = boardRepository.findById(boardId);
    if (isLike(nickname, boardId)) {
      delete(nickname, board);
      return;
    }

    create(nickname, board);
  }

  @Transactional(readOnly = true)
  public boolean isLike(String nickname, Long boardId) {
    return likeRepository.existsByBoardIdAndNickname(boardId, nickname);
  }

  @Transactional
  protected void create(String nickname, Board board) {
    Likes likes = Likes.create(nickname, board.getId());

    likeRepository.save(likes);
    board.incrementLikeCount();
  }

  @Transactional
  protected void delete(String nickname, Board board) {
    likeRepository.delete(board.getId(), nickname);

    board.decrementLikeCount();
  }

}