package com.example.board.like.service;

import com.example.board.board.service.BoardService;
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
  private final BoardService boardService;

  @Transactional
  public void doLike(Long boardId) {
    if (isLike(boardId)) {
      delete(boardId);
    } else {
      create(boardId);
    }
  }

  @Transactional(readOnly = true)
  public boolean isLike(Long boardId) {
    Member member = SecurityUtil.getMember();
    //null 쓰지 마;;
    return likeRepository.findByBoardIdAndMember(boardId, member) != null;
  }

  @Transactional
  protected void create(Long boardId) {
    Member member = SecurityUtil.getMember();

    Likes likes = Likes.create()
        .boardId(boardId)
        .member(member)
        .build();

    likeRepository.save(likes);
    boardService.incrementLikeCount(boardId);
  }

  @Transactional
  protected void delete(Long boardId) {
    Member member = SecurityUtil.getMember();

    likeRepository.delete(boardId, member);
    boardService.decrementLikeCount(boardId);
  }

}