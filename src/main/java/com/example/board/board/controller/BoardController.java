package com.example.board.board.controller;

import com.example.board.board.dto.request.BoardCreateRequest;
import com.example.board.board.dto.request.BoardUpdateRequest;
import com.example.board.board.dto.response.BoardAndCommentGetResponse;
import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.service.BoardService;
import jakarta.validation.Valid;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

  private final BoardService boardService;

  @PostMapping("")
  public ResponseEntity<BoardGetResponse> create(
      @Valid @RequestBody BoardCreateRequest boardCreateRequest
  ) {
    BoardGetResponse boardGetResponse = boardService.create(boardCreateRequest);

    return ResponseEntity.status(HttpStatus.CREATED).body(boardGetResponse);
  }

  @GetMapping("/{id}")
  public ResponseEntity<BoardAndCommentGetResponse> read(@PathVariable("id") Long id) {
    BoardAndCommentGetResponse boardAndCommentGetResponse = boardService.read(id);

    return ResponseEntity.status(HttpStatus.FOUND).body(boardAndCommentGetResponse);
  }

  @PatchMapping("/{id}")
  public ResponseEntity<BoardGetResponse> update(
      @PathVariable("id") Long id,
      @RequestBody @Valid BoardUpdateRequest boardUpdateRequest
  ) {
    BoardGetResponse boardGetResponse = boardService.update(id, boardUpdateRequest);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).body(boardGetResponse);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    boardService.delete(id);

    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("/list")
  public ResponseEntity<List<BoardGetResponse>> page(
      @RequestParam(defaultValue = "1")Long boardId,
      @RequestParam(defaultValue = "create_time", required = false) String sort,
      @RequestParam(defaultValue = "desc", required = false) String direction,
      @RequestParam(defaultValue = "5") int pageSize
  ) {
    List<BoardGetResponse> list = boardService.getList(boardId,sort, direction, pageSize);

    return ResponseEntity.status(HttpStatus.OK).body(list);
  }

}