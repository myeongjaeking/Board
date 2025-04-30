package com.example.board.comment.dto.response;

import lombok.Builder;

@Builder
public record CommentGetResponse(
    String content,
    String nickname,
    Long boardId
) {
}