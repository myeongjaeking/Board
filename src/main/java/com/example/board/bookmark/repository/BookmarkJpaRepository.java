package com.example.board.bookmark.repository;

import com.example.board.bookmark.entity.Bookmark;
import com.example.board.member.entity.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkJpaRepository extends JpaRepository<Bookmark, Long> {

  Optional<Bookmark> findByBoardId(Long boardId);

  @Query("SELECT b.boardId FROM Bookmark b WHERE b.nickname = :nickname")
  List<Long> findBoardIdByMember(String nickname);

  boolean existsByBoardIdAndNickname(Long boardId, String nickname);

}