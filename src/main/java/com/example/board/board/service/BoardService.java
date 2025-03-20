package com.example.board.board.service;

import com.example.board.board.dto.request.BoardRequest;
import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.entity.Board;
import com.example.board.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public Board create(BoardRequest boardRequest){
        Board board = Board.create()
                .title(boardRequest.title())
                .content(boardRequest.content())
                .build();
        boardRepository.save(board);

        return board;
    }

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

}