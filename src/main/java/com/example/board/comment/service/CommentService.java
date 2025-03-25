package com.example.board.comment.service;

import com.example.board.comment.dto.request.CommentCreateRequest;
import com.example.board.comment.dto.request.CommentUpdateRequest;
import com.example.board.comment.dto.response.CommentGetResponse;
import com.example.board.comment.entity.Comment;
import com.example.board.comment.repository.CommentRepository;
import com.example.board.global.common.SecurityUtil;
import com.example.board.member.entity.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

  private final CommentRepository commentRepository;

  @Transactional
  public CommentGetResponse create(Long boardId, CommentCreateRequest commentCreateRequest) {
    Member member = SecurityUtil.getMember();

    Comment comment = Comment.create()
        .boardId(boardId)
        .content(commentCreateRequest.content())
        .member(member)
        .build();

    commentRepository.save(comment);

    return CommentGetResponse.from(comment);
  }

  @Transactional(readOnly = true)
  public List<CommentGetResponse> getCommentList(Long boardId) {
    List<Comment> commentList = commentRepository.findByBoardId(boardId);

    return commentList.stream()
        .map(CommentGetResponse::from)
        .toList();
  }

  @Transactional
  public CommentGetResponse update(Long boardId, Long id,
      CommentUpdateRequest commentUpdateRequest) {
    Comment comment = commentRepository.findByBoardIdAndId(boardId,id);

    comment.updateContent(commentUpdateRequest.content());

    return CommentGetResponse.from(comment);
  }

  @Transactional
  public Long delete(Long boardId, Long id) {
    commentRepository.delete(boardId,id);

    return id;
  }

}