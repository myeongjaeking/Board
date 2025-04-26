package com.example.board.bookmark.service;

import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.entity.Board;
import com.example.board.board.service.BoardRepository;
import com.example.board.bookmark.entity.Bookmark;
import com.example.board.global.common.SecurityUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarkService {

  private final BookmarkRepository bookmarkRepository;
  private final BoardRepository boardRepository;

  @Transactional
  public void doRegister(String nickname,Long boardId) {
    if (isRegister(nickname,boardId)) {
      delete(nickname, boardId);
      return;
    }
    register(nickname, boardId);
  }

  @Transactional(readOnly = true)
  public boolean isRegister(String nickname,Long boardId) {
    return bookmarkRepository.existsByBoardIdAndNickname(boardId, nickname);
  }

  private void register(String nickname,Long boardId) {
    Bookmark bookmark = Bookmark.create(nickname,boardId);
    bookmarkRepository.save(bookmark);
  }

  @Transactional
  public void delete(String nickname,Long boardId) {
    bookmarkRepository.delete(boardId, nickname);
  }

  @Transactional(readOnly = true)
  public List<BoardGetResponse> getBookmarks() {
    String nickname = SecurityUtil.getNickname();

    List<Long> boardIds = bookmarkRepository.findBoardIdByMember(nickname);

    List<Board> boards = boardRepository.findAllById(boardIds);

    return boards.stream()
        .map(board -> BoardGetResponse.builder()
            .title(board.getTitle())
            .content(board.getContent())
            .createTime(board.getCreateTime())
            .viewCount(board.getViewCount())
            .build())
        .toList();
  }

}