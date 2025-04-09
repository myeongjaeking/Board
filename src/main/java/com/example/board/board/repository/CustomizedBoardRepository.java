package com.example.board.board.repository;

import com.example.board.board.dto.response.BoardGetResponse;
import java.util.List;

public interface CustomizedBoardRepository {

  List<BoardGetResponse> getBoardList(String sort, String direction, int page);

}