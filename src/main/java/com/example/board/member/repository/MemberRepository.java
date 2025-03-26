package com.example.board.member.repository;

import static com.example.board.global.exception.GlobalExceptionCode.NO_AUTHENTICATED;

import com.example.board.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

  private final MemberJpaRepository memberJpaRepository;

  public Member findByEmail(String email) {
    return memberJpaRepository.findByEmail(email).orElseThrow(NO_AUTHENTICATED::newException);
  }

  public Long save(Member member) {
    return memberJpaRepository.save(member).getId();
  }

  public Member findById(Long memberId) {
    return memberJpaRepository.findById(memberId).orElseThrow(NO_AUTHENTICATED::newException);
  }

}