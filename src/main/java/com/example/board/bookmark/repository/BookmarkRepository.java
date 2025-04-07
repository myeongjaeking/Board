package com.example.board.bookmark.repository;

import static com.example.board.global.exception.GlobalExceptionCode.NOT_FOUND_BOOKMARK;

import com.example.board.bookmark.entity.Bookmark;
import com.example.board.member.entity.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BookmarkRepository {

  private final BookmarkJpaRepository bookmarkJpaRepository;

  public void save(Bookmark bookmark) {
    bookmarkJpaRepository.save(bookmark);
  }

  public Bookmark findByBoardId(Long boardId) {
    return bookmarkJpaRepository.findByBoardId(boardId).orElseThrow(NOT_FOUND_BOOKMARK::newException);
  }

  public void delete(Long boardId, String nickname) {
    Bookmark bookmark = findByBoardId(boardId);

    bookmark.validateAccess(nickname);

    bookmarkJpaRepository.delete(bookmark);
  }

  public List<Long> findBoardIdByMember(String nickname) {
    return bookmarkJpaRepository.findBoardIdByMember(nickname);
  }

  public boolean existsByBoardIdAndNickname(Long boardId, String nickname) {
    return bookmarkJpaRepository.existsByBoardIdAndNickname(boardId, nickname);
  }

}