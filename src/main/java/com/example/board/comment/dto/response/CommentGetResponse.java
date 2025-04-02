package com.example.board.comment.dto.response;

import com.example.board.comment.entity.Comment;
import lombok.Builder;

@Builder
public record CommentGetResponse(
    String content,
    String nickname,
    Long boardId
) {

}