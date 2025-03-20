package com.example.board.board.controller;

import com.example.board.board.dto.request.BoardRequest;
import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.entity.Board;
import com.example.board.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/board/create")
    public ResponseEntity<Board> createBoard(@Valid @RequestBody BoardRequest boardRequest) {
        Board board = boardService.create(boardRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(board);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<BoardGetResponse> readBoard(@PathVariable("id") Long id) {
        BoardGetResponse boardGetResponse = boardService.read(id);

        return ResponseEntity.status(HttpStatus.OK).body(boardGetResponse);
    }

}