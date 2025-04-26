package com.example.board.board.service;

import com.example.board.board.dto.request.BoardCreateRequest;
import com.example.board.board.dto.request.BoardUpdateRequest;
import com.example.board.board.dto.response.BoardAndCommentGetResponse;
import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.entity.Board;
import com.example.board.comment.dto.response.CommentGetResponse;
import com.example.board.comment.entity.Comment;
import com.example.board.comment.service.CommentRepository;
import com.example.board.global.common.SecurityUtil;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

  private final BoardRepository boardRepository;
  private final CommentRepository commentRepository;

  public BoardGetResponse create(BoardCreateRequest boardCreateRequest) {
    String nickname = SecurityUtil.getNickname();

    Board board = Board.create(boardCreateRequest.title(),
        boardCreateRequest.content(),
        nickname);

    boardRepository.save(board);

    return BoardGetResponse.builder()
        .title(board.getTitle())
        .content(board.getContent())
        .createTime(board.getCreateTime())
        .viewCount(board.getViewCount())
        .build();
  }

  @Transactional
  public BoardAndCommentGetResponse read(Long id) {
    Board board = boardRepository.findById(id);

    List<CommentGetResponse> commentResponses = getComments(id);
    board.incrementViewCount();

    return BoardAndCommentGetResponse.builder()
        .title(board.getTitle())
        .content(board.getContent())
        .nickname(board.getNickname())
        .createTime(board.getCreateTime())
        .viewCount(board.getViewCount())
        .likeCount(board.getLikeCount())
        .commentList(commentResponses)
        .build();
  }

  @Transactional(readOnly = true)
  protected List<CommentGetResponse> getComments(Long id) {
    List<Comment> comments = commentRepository.findByBoardId(id);

    return comments.stream()
        .map(comment -> CommentGetResponse.builder()
            .content(comment.getContent())
            .nickname(comment.getNickname())
            .boardId(comment.getBoardId())
            .build()
        )
        .toList();
  }

  @Transactional
  public BoardGetResponse update(Long id, BoardUpdateRequest boardUpdateRequest) {
    String nickname = SecurityUtil.getNickname();
    Board board = boardRepository.findById(nickname, id);

    board.update(boardUpdateRequest.title(), boardUpdateRequest.content());

    return BoardGetResponse.builder()
        .title(board.getTitle())
        .content(board.getContent())
        .createTime(board.getCreateTime())
        .viewCount(board.getViewCount())
        .build();
  }

  @Transactional
  public void delete(Long id) {
    String nickname = SecurityUtil.getNickname();

    boardRepository.delete(nickname, id);
  }

  @Transactional(readOnly = true)
  public List<BoardGetResponse> getList(
      Long boardId,
      String sort,
      String direction,
      int pageSize
  ) {
    return boardRepository.getBoardList(boardId, sort, direction, pageSize);
  }

}