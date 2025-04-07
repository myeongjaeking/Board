package com.example.board.like.entity;

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
@Table(name = "likes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Likes {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nickname",nullable = false)
  private String nickname;

  @Column(name = "board_id",nullable = false)
  private Long boardId;

  @Builder(builderMethodName = "create")
  private Likes(String nickname, Long boardId){
    this.boardId = boardId;
    this.nickname = nickname;
  }

}