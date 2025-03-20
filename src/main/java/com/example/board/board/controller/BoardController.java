package com.example.board.board.controller;

import com.example.board.board.dto.request.BoardCreateRequest;
import com.example.board.board.dto.request.BoardUpdateRequest;
import com.example.board.board.dto.response.BoardGetResponse;
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
    public ResponseEntity<BoardGetResponse> create(@Valid @RequestBody BoardCreateRequest boardCreateRequest) {
        BoardGetResponse boardGetResponse = boardService.create(boardCreateRequest);

        return ResponseEntity.status(HttpStatus.CREATED).body(boardGetResponse);
    }

    @GetMapping("/board/{id}")
    public ResponseEntity<BoardGetResponse> read(@PathVariable("id") Long id) {
        BoardGetResponse boardGetResponse = boardService.read(id);

        return ResponseEntity.status(HttpStatus.FOUND).body(boardGetResponse);
    }

    @PatchMapping("/board/{id}")
    public ResponseEntity<BoardGetResponse> update(@PathVariable("id") Long id, @RequestBody @Valid BoardUpdateRequest boardUpdateRequest) {
        BoardGetResponse boardGetResponse = boardService.update(id, boardUpdateRequest);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(boardGetResponse);
    }

    @DeleteMapping("/board/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id")Long id){
        Long boardId = boardService.delete(id);

        return ResponseEntity.status(HttpStatus.OK).body(boardId);
    }

}