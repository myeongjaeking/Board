package com.example.board.member.entity;

import static com.example.board.global.exception.GlobalExceptionCode.NOT_MATCH_PASSWORD;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "members")
@Entity
public class Member {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nickname", nullable = false)
  private String nickname;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "refresh_token")
  private String refreshToken;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = false)
  private Role role;

  @Builder(builderMethodName = "create")
  private Member(String email, String password, String nickname) {
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.role = Role.MEMBER;
  }

  public static Member create(
      String email,
      String password,
      String nickname) {
    return new Member(email, password, nickname);
  }

  public void updateRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void updateNickname(String nickname) {
    this.nickname = nickname;
  }

  public void validatePassword(String password) {
    if (!this.password.equals(password)) {
      throw NOT_MATCH_PASSWORD.newException();
    }
  }

}