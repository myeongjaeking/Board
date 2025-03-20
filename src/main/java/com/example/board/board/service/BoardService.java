package com.example.board.board.service;

import com.example.board.board.dto.request.BoardCreateRequest;
import com.example.board.board.dto.request.BoardUpdateRequest;
import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardGetResponse create(BoardCreateRequest boardCreateRequest){
        Board board = Board.create()
                .title(boardCreateRequest.title())
                .content(boardCreateRequest.content())
                .build();
        boardRepository.save(board);

        return BoardGetResponse.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .createTime(board.getCreateTime())
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .build();
    }

    @Transactional
    public BoardGetResponse read(Long id){
        Board board = boardRepository.findById(id);

        board.incrementViewCount();

        return BoardGetResponse.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .createTime(board.getCreateTime())
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .build();
    }

    @Transactional
    public BoardGetResponse update(Long id, BoardUpdateRequest boardUpdateRequest){
        Board board = boardRepository.findById(id);
        board.update(boardUpdateRequest.title(), boardUpdateRequest.content());

        return BoardGetResponse.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .createTime(board.getCreateTime())
                .viewCount(board.getViewCount())
                .likeCount(board.getLikeCount())
                .build();
    }

}