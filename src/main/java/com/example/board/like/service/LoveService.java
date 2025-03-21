package com.example.board.like.service;

import com.example.board.board.service.BoardService;
import com.example.board.like.entity.Love;
import com.example.board.like.repository.LoveRepository;
import com.example.board.member.entity.Member;
import com.example.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoveService {

  private final LoveRepository loveRepository;
  private final MemberService memberService;
  private final BoardService boardService;

  @Transactional
  public void doLike(Long boardId) {
    if(isLike(boardId)){
      delete(boardId);
    } else {
      create(boardId);
    }
  }
  @Transactional(readOnly = true)
  public boolean isLike(Long boardId) {
    Member member = memberService.getMember();

    return loveRepository.findLikeByBoardIdAndMember(boardId, member) != null;
  }



  @Transactional
  protected void create(Long boardId) {
    Member member = memberService.getMember();

    Love love = Love.create()
        .boardId(boardId)
        .member(member)
        .build();

    loveRepository.save(love);
    boardService.incrementLikeCount(boardId);
  }

  @Transactional
  protected void delete(Long boardId) {
    Member member = memberService.getMember();

    loveRepository.delete(boardId, member);
    boardService.decrementLikeCount(boardId);
  }

}