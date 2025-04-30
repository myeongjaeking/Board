package com.example.board.board.repository;

import com.example.board.board.entity.Board;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardJpaRepository extends JpaRepository<Board, Long>, CustomizedBoardRepository {

  @Lock(LockModeType.PESSIMISTIC_WRITE)
  @Query("select b from Board b where b.id = :boardId")
  Board findWithPessimisticWriteById(@Param("boardId") Long boardId);

}