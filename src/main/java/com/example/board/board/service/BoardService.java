package com.example.board.board.service;

import com.example.board.board.dto.request.BoardCreateRequest;
import com.example.board.board.dto.request.BoardUpdateRequest;
import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.dto.response.BoardPageResponse;
import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import com.example.board.global.config.jwt.SecurityUtil;
import com.example.board.member.entity.Member;
import com.example.board.member.repository.MemberRepository;
import com.example.board.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

  private final BoardRepository boardRepository;
  private final MemberService memberService;

  @Transactional
  public BoardGetResponse create(BoardCreateRequest boardCreateRequest) {
    Member member = memberService.getMember();

    Board board = Board.create()
        .title(boardCreateRequest.title())
        .content(boardCreateRequest.content())
        .member(member)
        .build();
    boardRepository.save(board);

    return BoardGetResponse.from(board);
  }

  @Transactional
  public BoardGetResponse read(Long id) {
    Member member = memberService.getMember();
    Board board = boardRepository.findBoardByMemberAndId(member,id);

    board.incrementViewCount();

    return BoardGetResponse.from(board);
  }

  @Transactional
  public BoardGetResponse update(Long id, BoardUpdateRequest boardUpdateRequest) {
    Member member = memberService.getMember();
    Board board = boardRepository.findBoardByMemberAndId(member,id);

    board.update(boardUpdateRequest.title(), boardUpdateRequest.content());

    return BoardGetResponse.from(board);
  }

  @Transactional
  public Long delete(Long id) {
    Member member = memberService.getMember();
    boardRepository.delete(member,id);

    return id;
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