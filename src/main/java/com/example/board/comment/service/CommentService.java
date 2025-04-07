package com.example.board.comment.service;

import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.comment.dto.request.CommentCreateRequest;
import com.example.board.comment.dto.request.CommentUpdateRequest;
import com.example.board.comment.dto.response.CommentGetResponse;
import com.example.board.comment.entity.Comment;
import com.example.board.comment.repository.CommentRepository;
import com.example.board.global.common.SecurityUtil;
import com.example.board.member.entity.Member;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

  private final CommentRepository commentRepository;

  public CommentGetResponse create(Long boardId, CommentCreateRequest commentCreateRequest) {
    String nickname = SecurityUtil.getNickname();

    Comment comment = Comment.create()
        .boardId(boardId)
        .content(commentCreateRequest.content())
        .nickname(nickname)
        .build();

    commentRepository.save(comment);

    return CommentGetResponse.builder()
        .boardId(comment.getBoardId())
        .content(comment.getContent())
        .nickname(comment.getNickname())
        .build();
  }

  @Transactional(readOnly = true)
  public List<CommentGetResponse> getCommentList(Long boardId) {
    List<Comment> commentList = commentRepository.findByBoardId(boardId);

    return commentList.stream()
        .map(comment -> CommentGetResponse.builder()
            .boardId(comment.getBoardId())
            .content(comment.getContent())
            .nickname(comment.getNickname())
            .build())
        .toList();
  }

  @Transactional
  public CommentGetResponse update(
      Long boardId,
      Long id,
      CommentUpdateRequest commentUpdateRequest
  ) {
    String nickname = SecurityUtil.getNickname();
    Comment comment = commentRepository.findByBoardIdAndId(boardId, id, nickname);

    comment.updateContent(commentUpdateRequest.content());

    return CommentGetResponse.builder()
        .content(comment.getContent())
        .nickname(comment.getNickname())
        .boardId(comment.getBoardId())
        .build();
  }

  @Transactional
  public void delete(Long boardId, Long commentId) {
    String nickname = SecurityUtil.getNickname();

    commentRepository.delete(boardId, commentId, nickname);
  }

}