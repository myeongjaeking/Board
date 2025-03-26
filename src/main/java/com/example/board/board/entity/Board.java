package com.example.board.board.entity;

import static com.example.board.global.exception.GlobalExceptionCode.FORBIDDEN_ACCESS_BOARD;

import com.example.board.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//연관관게 공부하기.
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

  @Column(name = "content", nullable = false)
  private String content;

  @Column(name = "view_count", nullable = false)
  private int viewCount;

  @Column(name = "like_count", nullable = false)
  private int likeCount;

  @Column(name = "create_time", nullable = false)
  private LocalDateTime createTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Builder(builderMethodName = "create")
  private Board(String title, String content, Member member) {
    this.title = title;
    this.content = content;
    this.createTime = LocalDateTime.now();
    this.member = member;
    this.viewCount = 0;
    this.likeCount = 0;
  }

  public void incrementViewCount() {
    this.viewCount++;
  }

  public void incrementLikeCount() {
    this.likeCount++;
  }

  public void decrementLikeCount() {
    this.likeCount--;
  }

  public void update(String title, String content) {
    this.title = title;
    this.content = content;
  }

  public void validateAccess(Member member){
    if(this.member.equals(member)){
      throw FORBIDDEN_ACCESS_BOARD.newException();
    }
  }

}