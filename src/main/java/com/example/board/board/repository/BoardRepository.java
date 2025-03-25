package com.example.board.board.repository;

import com.example.board.board.entity.Board;
import com.example.board.global.exception.CustomException;
import com.example.board.member.entity.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.example.board.global.exception.GlobalExceptionCode.NOT_FOUND_BOARD;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

  private final BoardJpaRepository boardJpaRepository;

  public void save(Board board) {
    boardJpaRepository.save(board);
  }

  public Board findByMemberAndId(Member member, Long id) {

    return boardJpaRepository.findByMemberAndId(member, id)
        .orElseThrow(() -> new CustomException(NOT_FOUND_BOARD));
  }

  public List<Board> findAllById(List<Long> id) {
    return boardJpaRepository.findAllById(id);
  }

  public void delete(Member member,Long id) {
    boardJpaRepository.findByMemberAndId(member,id).orElseThrow(() -> new CustomException(NOT_FOUND_BOARD));

    boardJpaRepository.deleteById(id);
  }

  public Page<Board> findAll(Pageable pageable) {
    return boardJpaRepository.findAll(pageable);
  }

  public Board findById(Long id){
    return boardJpaRepository.findById(id).orElseThrow();
  }

}