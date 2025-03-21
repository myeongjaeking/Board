package com.example.board.like.repository;

import com.example.board.like.entity.Love;
import com.example.board.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoveJpaRepository extends JpaRepository<Love, Long> {

  Love findLikeByBoardIdAndMember(Long boardId, Member member);

}
