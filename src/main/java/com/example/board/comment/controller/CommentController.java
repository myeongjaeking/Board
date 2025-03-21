package com.example.board.comment.controller;

import com.example.board.comment.dto.request.CommentCreateRequest;
import com.example.board.comment.dto.response.CommentGetResponse;
import com.example.board.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards/{boardId}/comments")
public class CommentController {

  private final CommentService commentService;

  @PostMapping("/create")
  public ResponseEntity<CommentGetResponse> create(@PathVariable("boardId") Long boardId,
      @RequestBody CommentCreateRequest commentCreateRequest) {
    CommentGetResponse commentGetResponse = commentService.create(boardId,commentCreateRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(commentGetResponse);
  }


}
