package com.example.board.board.repository;

import com.example.board.board.entity.Board;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardJpaRepository extends JpaRepository<Board, Long> {

  Optional<Board> findById(Long id);

  Page<Board> findAll(Pageable pageable);

}