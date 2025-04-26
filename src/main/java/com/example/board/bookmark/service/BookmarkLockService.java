package com.example.board.bookmark.service;

import com.example.board.board.repository.BoardLockJpaRepository;
import com.example.board.global.common.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BookmarkLockService {

  private final BoardLockJpaRepository boardLockJpaRepository;
  private final BookmarkService bookmarkService;

  @Transactional
  public void bookmarkBoardLock(Long boardId) {
    String nickname = SecurityUtil.getNickname();

    try {
      boardLockJpaRepository.getLock(boardId.toString());
      bookmarkService.doRegister(nickname, boardId);
    } finally {
      boardLockJpaRepository.releaseLock(boardId.toString());
    }
  }

}