package com.example.board.service;

import com.example.board.domain.Board;
import com.example.board.dto.BoardCreateRequest;
import com.example.board.dto.BoardGetResponse;
import com.example.board.dto.BoardPageResponse;
import com.example.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long createBoard(BoardCreateRequest boardCreateRequest) {
        Board board = Board.create()
                .title(boardCreateRequest.title())
                .content(boardCreateRequest.content())
                .build();
        boardRepository.save(board);
        return board.getId();


    }

    @Transactional
    public BoardGetResponse readBoard(Long id)  {
        Board board = findBoardById(id);
        board.incrementViewCount();
       return BoardGetResponse.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .createBoard(board.getCreateBoard())
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .build();

    }

    @Transactional
    public BoardGetResponse likeBoard(Long id) {
        Board board = findBoardById(id);
        return BoardGetResponse.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .createBoard(board.getCreateBoard())
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .build();
    }

    public void delete(Long id){
        Board board = findBoardById(id);
        boardRepository.delete(board);
    }

    @Transactional
    public void update(Long id, BoardCreateRequest boardCreateRequest){
        Board board = findBoardById(id);
        board.update(boardCreateRequest.title(), boardCreateRequest.content());
    }

    public Board findBoardById(Long id){
        Board board = boardRepository.findById(id).orElseThrow(()->
                new IllegalArgumentException("해당 그룹을 찾을 수 없습니다."));
       return board;
    }
    public Page<BoardPageResponse> getList(int page){
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createBoard"));
        Pageable pageable = PageRequest.of(page,5,Sort.by(sorts));
        Page<Board> boards = boardRepository.findAll(pageable);
        Page<BoardPageResponse> boardPageResponses = boards.map(board ->
                new BoardPageResponse(
                        board.getTitle(),
                        board.getId(),
                        board.getCreateBoard(),
                        board.getViewCount()
                ));
        return boardPageResponses;
    }

}

