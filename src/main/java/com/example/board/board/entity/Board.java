package com.example.board.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "boards")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "title", nullable = false)
  private String title;

  @Column(name = "content", columnDefinition = "TEXT")
  private String content;

  @Column(name = "view_count", nullable = false)
  private int viewCount;

  @Column(name = "like_count", nullable = false)
  private int likeCount;

  @Column(name = "create_time", nullable = false)
  private LocalDateTime createTime;

  @Builder(builderMethodName = "create")
  public Board(String title, String content) {
    this.title = title;
    this.content = content;
    this.createTime = LocalDateTime.now();
    this.viewCount = 0;
    this.likeCount = 0;
  }

  public void incrementViewCount() {
    this.viewCount++;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }

}