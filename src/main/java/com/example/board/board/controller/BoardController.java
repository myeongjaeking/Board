package com.example.board.board.controller;

import com.example.board.board.dto.request.BoardCreateRequest;
import com.example.board.board.dto.request.BoardUpdateRequest;
import com.example.board.board.dto.response.BoardAndCommentGetResponse;
import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.dto.response.BoardPageResponse;
import com.example.board.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

  private final BoardService boardService;

  @PostMapping("/create")
  public ResponseEntity<BoardGetResponse> create(
      @Valid @RequestBody BoardCreateRequest boardCreateRequest) {
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

  //TODO QueryDSL Study
  @GetMapping("/list")
  public ResponseEntity<Page<BoardPageResponse>> page(
      @PageableDefault(page = 0, size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable page) {
    Page<BoardPageResponse> list = boardService.getList(page);

    return ResponseEntity.status(HttpStatus.OK).body(list);
  }

}