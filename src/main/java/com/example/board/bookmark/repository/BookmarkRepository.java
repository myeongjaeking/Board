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

  public Bookmark findByBoardIdAndMember(Long boardId, Member member) {
    return bookmarkJpaRepository.findByBoardIdAndMember(boardId, member);
  }

  public void delete(Long boardId, Member member) {
    Bookmark bookmark = findByBoardIdAndMember(boardId,member);

    bookmarkJpaRepository.delete(bookmark);
  }

  public List<Long> findBoardIdByMember(Member member) {
    return bookmarkJpaRepository.findBoardIdByMember(member);
  }

}