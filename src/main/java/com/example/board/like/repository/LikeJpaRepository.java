package com.example.board.like.repository;

import com.example.board.like.entity.Likes;
import com.example.board.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeJpaRepository extends JpaRepository<Likes, Long> {

  Likes findByBoardIdAndMember(Long boardId, Member member);

}