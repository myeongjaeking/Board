package com.example.board.board.repository;

import static com.example.board.global.exception.GlobalExceptionCode.NOT_FOUND_BOARD;

import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.entity.Board;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

  private final BoardJpaRepository boardJpaRepository;

  public void save(Board board) {
    boardJpaRepository.save(board);
  }

  public Board findById(Long id) {
    return boardJpaRepository.findById(id)
        .orElseThrow(NOT_FOUND_BOARD::newException);
  }

  public Board findById(String nickname, Long id) {
    Board board = boardJpaRepository.findById(id)
        .orElseThrow(NOT_FOUND_BOARD::newException);

    board.validateAccess(nickname);

    return board;
  }

  public List<BoardGetResponse> getBoardList(String sort, String direction, int page) {
    return boardJpaRepository.getBoardList(sort,direction, page);
  }

  public List<Board> findAllById(List<Long> id) {
    return boardJpaRepository.findAllById(id);
  }

  public void delete(String nickname,Long id) {
    Board board = findById(nickname,id);

    boardJpaRepository.delete(board);
  }

}