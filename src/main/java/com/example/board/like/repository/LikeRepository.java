package com.example.board.like.repository;

import com.example.board.like.entity.Likes;
import com.example.board.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

  private final LikeJpaRepository likeJpaRepository;

  public void save(Likes likes) {
    likeJpaRepository.save(likes);
  }

  public void delete(Long boardId, Member member) {
    Likes likes = findByBoardIdAndMember(boardId,member);
    likeJpaRepository.delete(likes);
  }

  public Likes findByBoardIdAndMember(Long boardId, Member member) {
    return likeJpaRepository.findByBoardIdAndMember(boardId, member).orElseThrow();
  }

  public boolean existsByBoardIdAndMember(Long boardId, Member member) {
    return likeJpaRepository.existsByBoardIdAndMember(boardId, member);
  }

}