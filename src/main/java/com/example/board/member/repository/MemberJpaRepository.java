package com.example.board.member.repository;

import com.example.board.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

//Optional<Member>
public interface MemberJpaRepository extends JpaRepository<Member, Long> {

  Member findByEmail(String email);

}