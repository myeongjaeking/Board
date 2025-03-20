package com.example.board.board.repository;

import com.example.board.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

  Page<Board> findAll(Pageable pageable);

}