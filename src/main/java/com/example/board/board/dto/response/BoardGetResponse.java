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

}