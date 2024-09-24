package com.example.board.controller;

import com.example.board.service.BoardService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {
    private final BoardService boardService;
}
