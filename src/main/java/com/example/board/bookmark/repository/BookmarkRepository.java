package com.example.board.bookmark.repository;

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

  public Bookmark findBookmarkByBoardIdAndMember(Long boardId, Member member) {
    return bookmarkJpaRepository.findBookmarkByBoardIdAndMember(boardId, member);
  }

  public void delete(Long boardId, Member member) {
    Bookmark bookmark = findBookmarkByBoardIdAndMember(boardId,member);

    bookmarkJpaRepository.delete(bookmark);
  }

  public List<Long> findBoardIdByMember(Member member) {
    return bookmarkJpaRepository.findBoardIdByMember(member);
  }

}