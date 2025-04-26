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
  public Long doRegister(Long boardId) {
    if (isRegister(boardId)) {
      return delete(boardId);
    }
    return register(boardId);
  }

  @Transactional(readOnly = true)
  public boolean isRegister(Long boardId) {
    String nickname = SecurityUtil.getNickname();

    return bookmarkRepository.existsByBoardIdAndNickname(boardId, nickname);
  }

  private Long register(Long boardId) {
    String nickname = SecurityUtil.getNickname();

    Bookmark bookmark = Bookmark.create(nickname,boardId);

    bookmarkRepository.save(bookmark);

    return bookmark.getBoardId();
  }

  @Transactional
  public Long delete(Long boardId) {
    String nickname = SecurityUtil.getNickname();

    bookmarkRepository.delete(boardId, nickname);
    return boardId;
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