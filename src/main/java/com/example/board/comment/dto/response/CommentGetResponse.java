package com.example.board.comment.dto.response;

import com.example.board.comment.entity.Comment;
import lombok.Builder;

@Builder
public record CommentGetResponse(
    String content,
    String nickname,
    Long boardId
) {

  public static CommentGetResponse from(Comment comment) {
    return CommentGetResponse.builder()
        .content(comment.getContent())
        .nickname(comment.getMember().getNickname())
        .boardId(comment.getBoardId())
        .build();
  }

}