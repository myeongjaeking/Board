package com.example.board.like.service;

import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import com.example.board.global.common.SecurityUtil;
import com.example.board.like.entity.Likes;
import com.example.board.like.repository.LikeRepository;
import com.example.board.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LikeService {

  private final LikeRepository likeRepository;
  private final BoardRepository boardRepository;

  @Transactional
  public void doLike(Long boardId) {
    if (isLike(boardId)) {
      delete(boardId);
      return;
    }
    create(boardId);
  }

  @Transactional(readOnly = true)
  public boolean isLike(Long boardId) {
    Member member = SecurityUtil.getMember();

    return likeRepository.existsByBoardIdAndMember(boardId,member);
  }

  @Transactional
  protected void create(Long boardId) {
    Member member = SecurityUtil.getMember();

    Likes likes = Likes.create()
        .boardId(boardId)
        .member(member)
        .build();

    likeRepository.save(likes);

    Board board = boardRepository.findById(boardId);
    board.incrementLikeCount();
  }

  @Transactional
  protected void delete(Long boardId) {
    Member member = SecurityUtil.getMember();

    likeRepository.delete(boardId, member);

    Board board = boardRepository.findById(boardId);
    board.decrementLikeCount();
  }

}