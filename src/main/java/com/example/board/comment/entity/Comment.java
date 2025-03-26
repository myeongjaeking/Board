package com.example.board.comment.entity;

import static com.example.board.global.exception.GlobalExceptionCode.FORBIDDEN_ACCESS_COMMENT;

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

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @Column(name = "board_id", nullable = false)
  private Long boardId;

  @Builder(builderMethodName = "create")
  private Comment(String content, Member member, Long boardId) {
    this.content = content;
    this.member = member;
    this.boardId = boardId;
  }

  public void updateContent(String content) {
    this.content = content;
  }

  public void validateAccess(Member member) {
    if(this.member.equals(member)) {
      throw FORBIDDEN_ACCESS_COMMENT.newException();
    }
  }

}