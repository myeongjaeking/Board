package com.example.board.board.dto.response;

import com.example.board.board.entity.Board;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record BoardGetResponse(
    String title,
    String content,
    LocalDateTime createTime,
    String nickname,
    int viewCount,
    int likeCount
) {

  public static BoardGetResponse from(Board board) {
    return BoardGetResponse.builder()
        .title(board.getTitle())
        .content(board.getContent())
        .createTime(board.getCreateTime())
        .nickname(board.getMember().getNickname())
        .viewCount(board.getViewCount())
        .likeCount(board.getLikeCount())
        .build();
  }

}