package com.example.board.comment.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateRequest(
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    String content
) {

}