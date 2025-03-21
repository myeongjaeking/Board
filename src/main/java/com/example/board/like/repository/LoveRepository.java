package com.example.board.like.repository;

import com.example.board.like.entity.Love;
import com.example.board.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LoveRepository {

  private final LoveJpaRepository loveJpaRepository;

  public void save(Love love) {
    loveJpaRepository.save(love);
  }

  public void delete(Long boardId, Member member) {
    Love love = findLikeByBoardIdAndMember(boardId,member);
    loveJpaRepository.delete(love);
  }

  public Love findLikeByBoardIdAndMember(Long boardId, Member member) {
    return loveJpaRepository.findLikeByBoardIdAndMember(boardId, member);
  }

}
