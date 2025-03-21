package com.example.board.comment.service;

import com.example.board.comment.dto.request.CommentCreateRequest;
import com.example.board.comment.dto.response.CommentGetResponse;
import com.example.board.comment.entity.Comment;
import com.example.board.comment.repository.CommentRepository;
import com.example.board.member.entity.Member;
import com.example.board.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CommentService {

  private final CommentRepository commentRepository;
  private final MemberService memberService;

  @Transactional
  public CommentGetResponse create(Long boardId, CommentCreateRequest commentCreateRequest) {
    Member member = memberService.getMember();

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
    List<Comment> commentList = commentRepository.getList(boardId);

    return commentList.stream()
        .map(CommentGetResponse::from)
        .toList();
  }

}
