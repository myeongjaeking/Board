package com.example.board.bookmark.service;

import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.service.BoardService;
import com.example.board.bookmark.entity.Bookmark;
import com.example.board.bookmark.repository.BookmarkRepository;
import com.example.board.member.entity.Member;
import com.example.board.member.service.MemberService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

  private final BookmarkRepository bookmarkRepository;
  private final MemberService memberService;
  private final BoardService boardService;

  @Transactional
  public void doRegister(Long boardId) {
    if (isRegister(boardId)) {
      delete(boardId);
    } else {
      register(boardId);
    }
  }

  @Transactional
  public void register(Long boardId) {
    Member member = memberService.getMember();

    Bookmark bookmark = Bookmark.create()
        .boardId(boardId)
        .member(member)
        .build();

    bookmarkRepository.save(bookmark);
  }

  @Transactional(readOnly = true)
  public boolean isRegister(Long boardId) {
    Member member = memberService.getMember();

    return bookmarkRepository.findBookmarkByBoardIdAndMember(boardId, member) != null;
  }

  @Transactional
  public void delete(Long boardId) {
    Member member = memberService.getMember();

    bookmarkRepository.delete(boardId, member);
  }

  @Transactional(readOnly = true)
  public List<BoardGetResponse> getBookmarks() {
    Member member = memberService.getMember();

    List<Long> boardIds = bookmarkRepository.findBoardIdByMember(member);

    return boardIds.stream()
        .map(boardService::getBoardResponseById)
        .collect(Collectors.toList());
  }

}