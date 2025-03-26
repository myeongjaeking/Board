package com.example.board.board.service;

import com.example.board.board.dto.request.BoardCreateRequest;
import com.example.board.board.dto.request.BoardUpdateRequest;
import com.example.board.board.dto.response.BoardAndCommentGetResponse;
import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.dto.response.BoardPageResponse;
import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import com.example.board.comment.dto.response.CommentGetResponse;
import com.example.board.comment.entity.Comment;
import com.example.board.comment.repository.CommentRepository;
import com.example.board.global.common.SecurityUtil;
import com.example.board.member.entity.Member;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

  private final BoardRepository boardRepository;
  private final CommentRepository commentRepository;

  @Transactional
  public BoardGetResponse create(BoardCreateRequest boardCreateRequest) {
    Member member = SecurityUtil.getMember();

    Board board = Board.create()
        .title(boardCreateRequest.title())
        .content(boardCreateRequest.content())
        .member(member)
        .build();
    boardRepository.save(board);

    return BoardGetResponse.from(board);
  }

  @Transactional
  public BoardAndCommentGetResponse read(Long id) {
    Board board = boardRepository.findById(id);

    List<CommentGetResponse> commentResponses = getComments(id);
    board.incrementViewCount();

    return BoardAndCommentGetResponse.from(board, commentResponses);
  }

  @Transactional(readOnly = true)
  protected List<CommentGetResponse> getComments(Long id) {
    List<Comment> comments = commentRepository.findByBoardId(id);
    return comments.stream()
        .map(CommentGetResponse::from)
        .toList();
  }

  @Transactional
  public BoardGetResponse update(Long id, BoardUpdateRequest boardUpdateRequest) {
    Member member = SecurityUtil.getMember();
    Board board = boardRepository.findById(member, id);

    board.update(boardUpdateRequest.title(), boardUpdateRequest.content());

    return BoardGetResponse.from(board);
  }

  @Transactional
  public void delete(Long id) {
    Member member = SecurityUtil.getMember();
    boardRepository.delete(member, id);
  }

  @Transactional(readOnly = true)
  public Page<BoardPageResponse> getList(Pageable page) {
    Page<Board> boards = boardRepository.findAll(page);

    return boards.map(board ->
        new BoardPageResponse(
            board.getTitle(),
            board.getId(),
            board.getCreateTime(),
            board.getViewCount()
        ));
  }

}