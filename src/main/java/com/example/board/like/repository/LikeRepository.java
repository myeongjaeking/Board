package com.example.board.like.repository;

import com.example.board.like.entity.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LikeRepository {

  private final LikeJpaRepository likeJpaRepository;

  public void save(Likes likes) {
    likeJpaRepository.save(likes);
  }

  public void delete(Long boardId, String nickname) {
    Likes likes = findByBoardIdAndNickname(boardId,nickname);
    likeJpaRepository.delete(likes);
  }

  public Likes findByBoardIdAndNickname(Long boardId, String nickname) {
    return likeJpaRepository.findByBoardIdAndNickname(boardId, nickname).orElseThrow();
  }

  public boolean existsByBoardIdAndNickname(Long boardId, String nickname) {
    return likeJpaRepository.existsByBoardIdAndNickname(boardId, nickname);
  }

}