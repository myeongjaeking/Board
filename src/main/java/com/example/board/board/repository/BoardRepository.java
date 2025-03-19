package com.example.board.board.repository;

import com.example.board.board.entity.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    public void save(Board board) {
        boardJpaRepository.save(board);
    }

}