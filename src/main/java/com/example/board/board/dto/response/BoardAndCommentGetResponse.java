package com.example.board.board.dto.response;

import com.example.board.board.entity.Board;
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

  public static BoardAndCommentGetResponse from(Board board,
      List<CommentGetResponse> commentList) {
    return BoardAndCommentGetResponse.builder()
        .commentList(commentList)
        .title(board.getTitle())
        .content(board.getContent())
        .createTime(board.getCreateTime())
        .nickname(board.getMember().getNickname())
        .viewCount(board.getViewCount())
        .likeCount(board.getLikeCount())
        .commentList(commentList)
        .build();
  }

}