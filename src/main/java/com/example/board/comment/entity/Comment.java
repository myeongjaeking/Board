package com.example.board.comment.entity;

import static com.example.board.global.exception.GlobalExceptionCode.FORBIDDEN_ACCESS_COMMENT;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "nickname",nullable = false)
  private String nickname;

  @Column(name = "board_id", nullable = false)
  private Long boardId;

  @Builder(builderMethodName = "create")
  private Comment(String content, String nickname, Long boardId) {
    this.content = content;
    this.nickname = nickname;
    this.boardId = boardId;
  }

  public void updateContent(String content) {
    this.content = content;
  }

  public void validateAccess(String nickname) {
    if(this.nickname.equals(nickname)) {
      throw FORBIDDEN_ACCESS_COMMENT.newException();
    }
  }

}