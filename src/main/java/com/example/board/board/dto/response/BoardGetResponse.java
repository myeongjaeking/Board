package com.example.board.board.dto.response;

import com.example.board.board.entity.Board;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardGetResponse(
    String title,
    String content,
    LocalDateTime createTime,
    int viewCount,
    int likeCount
) {

  public static BoardGetResponse from(Board board) {
    return BoardGetResponse.builder()
        .title(board.getTitle())
        .content(board.getContent())
        .createTime(board.getCreateTime())
        .viewCount(board.getViewCount())
        .likeCount(board.getLikeCount())
        .build();
  }

}