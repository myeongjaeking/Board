package com.example.board.board.dto.response;

import com.example.board.comment.dto.response.CommentGetResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;

@Builder
public record BoardAndCommentGetResponse(
    String title,
    String content,
    LocalDateTime createTime,
    String nickname,
    int viewCount,
    int likeCount,
    List<CommentGetResponse> commentList
) {

}