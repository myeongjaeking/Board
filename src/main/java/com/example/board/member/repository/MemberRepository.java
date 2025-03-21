package com.example.board.member.repository;

import com.example.board.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MemberRepository {

  private final MemberJpaRepository memberJpaRepository;

  public Member findByRefreshToken(String token) {
    return memberJpaRepository.findByRefreshToken(token);
  }

  public Member findByEmail(String email) {
    return memberJpaRepository.findByEmail(email);
  }

  public void save(Member member) {
    memberJpaRepository.save(member);
  }

  public Member findById(Long memberId) {
    return memberJpaRepository.findById(memberId).orElseThrow();
  }

}