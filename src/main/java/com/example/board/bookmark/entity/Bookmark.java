package com.example.board.bookmark.entity;

import static com.example.board.global.exception.GlobalExceptionCode.FORBIDDEN_ACCESS_BOOKMARK;

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
@Table(name = "bookmarks")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Bookmark {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nickname",nullable = false)
  private String nickname;

  @Column(name = "board_id", nullable = false)
  private Long boardId;

  @Builder(builderMethodName = "create")
  private Bookmark(String nickname, Long boardId) {
    this.boardId = boardId;
    this.nickname = nickname;
  }

  public void validateAccess(String nickname){
    if(this.nickname.equals(nickname)){
      throw FORBIDDEN_ACCESS_BOOKMARK.newException();
    }
  }

}