package com.example.board.controller;

import com.example.board.domain.Board;
import com.example.board.dto.BoardCreateRequest;
import com.example.board.dto.BoardGetResponse;
import com.example.board.dto.BoardPageResponse;
import com.example.board.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boardCreate")
    public ResponseEntity<Long> create(@RequestBody @Valid BoardCreateRequest boardCreateRequest) {
        Long boardId = boardService.createBoard(boardCreateRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(boardId);
    }

    @GetMapping("/{id}/boardRead")
    public BoardGetResponse read(@PathVariable("id") Long id) {
        try {
            return boardService.readBoard(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @PatchMapping("/{id}/boardLikes")
    public BoardGetResponse like(@PathVariable("id") Long id) {
        try {
            return boardService.likeBoard(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}/boardDelete")
    public ResponseEntity<?> delete(@PathVariable("id") Long id) throws Exception {
        boardService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
    @PatchMapping("/{id}/boardUpdate")
    public ResponseEntity<Void> update(@PathVariable("id")Long id, @RequestBody @Valid BoardCreateRequest boardCreateRequest)
    {
        boardService.update(id,boardCreateRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/boarList")
    public ResponseEntity<Page<BoardPageResponse>> pasing(@RequestParam(value="page", defaultValue="0") int page){

        return ResponseEntity.ok(this.boardService.getList(page));
    }



}

