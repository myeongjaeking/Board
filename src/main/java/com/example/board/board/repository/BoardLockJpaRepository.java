package com.example.board.board.repository;

import com.example.board.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardLockJpaRepository extends JpaRepository<Board,Long> {

  @Query(value = "select get_lock(:key, 3000)",nativeQuery = true)
  void getLock(String key);

  @Query(value = "select release_lock(:key)",nativeQuery = true)
  void releaseLock(String key);

}