package com.example.board.board.repository;

import com.example.board.board.entity.Board;
import com.example.board.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.example.board.global.exception.GlobalExceptionCode.NOT_FOUND_BOARD;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final BoardJpaRepository boardJpaRepository;

    public void save(Board board) {
        boardJpaRepository.save(board);
    }

    public Board findById(Long id){
        Optional<Board> optionalBoard = boardJpaRepository.findById(id);
        if(optionalBoard.isEmpty()){
            throw new CustomException(NOT_FOUND_BOARD);
        }

        return optionalBoard.get();
    }

    public Long delete(Long id){
        Optional<Board> optionalBoard = boardJpaRepository.findById(id);
        if(optionalBoard.isEmpty()){
            throw new CustomException(NOT_FOUND_BOARD);
        }

        boardJpaRepository.deleteById(id);

        return id;
    }

}