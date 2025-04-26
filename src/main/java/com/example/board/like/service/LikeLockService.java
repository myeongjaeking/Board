package com.example.board.like.service;

import com.example.board.board.repository.BoardLockJpaRepository;
import com.example.board.global.common.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LikeLockService {

  private final BoardLockJpaRepository boardLockJpaRepository;
  private final LikeService likeService;

  @Transactional
  public void likeBoardLock(Long boardId) {
    String nickname = SecurityUtil.getNickname();

    try {
      boardLockJpaRepository.getLock(boardId.toString());
      likeService.doLike(nickname, boardId);
    } finally {
      boardLockJpaRepository.releaseLock(boardId.toString());
    }
  }

}