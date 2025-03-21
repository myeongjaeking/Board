package com.example.board.comment.controller;

import com.example.board.comment.dto.request.CommentCreateRequest;
import com.example.board.comment.dto.request.CommentUpdateRequest;
import com.example.board.comment.dto.response.CommentGetResponse;
import com.example.board.comment.service.CommentService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
    CommentGetResponse commentGetResponse = commentService.create(boardId, commentCreateRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(commentGetResponse);
  }

  @GetMapping("/list")
  public ResponseEntity<List<CommentGetResponse>> read(@PathVariable("boardId") Long boardId) {
    List<CommentGetResponse> commentGetResponses = commentService.getCommentList(boardId);

    return ResponseEntity.status(HttpStatus.OK).body(commentGetResponses);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<CommentGetResponse> update(@PathVariable("boardId") Long boardId,
      @PathVariable("id") Long id, @RequestBody
  CommentUpdateRequest commentUpdateRequest) {
    CommentGetResponse commentGetResponse = commentService.update(boardId,id,commentUpdateRequest);

    return ResponseEntity.status(HttpStatus.OK).body(commentGetResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Long> delete(@PathVariable("boardId") Long boardId,
      @PathVariable("id") Long id) {
    Long commentId = commentService.delete(boardId,id);

    return ResponseEntity.status(HttpStatus.OK).body(commentId);
  }

}
