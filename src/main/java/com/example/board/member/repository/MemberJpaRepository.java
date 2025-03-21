package com.example.board.member.repository;

import com.example.board.member.entity.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

  Member findByRefreshToken(String token);

  Member findByEmail(String email);

  Optional<Member> findByNickname(String username);

}