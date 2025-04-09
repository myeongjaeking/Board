package com.example.board.board.repository;

import static com.querydsl.core.types.Projections.constructor;

import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.entity.QBoard;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomizedBoardRepositoryImpl implements CustomizedBoardRepository{

  private final JPAQueryFactory jpaQueryFactory;
  private final int SIZE = 5;

  @Override
  public List<BoardGetResponse> getBoardList(
      String sort,
      String direction,
      int page
  ) {

  QBoard board = QBoard.board;

    return jpaQueryFactory
        .select(
            constructor(BoardGetResponse.class,
            board.id,
            board.title,
            board.content,
            board.createTime,
            board.viewCount))
        .from(board)
        .orderBy(createOrderSpecifier(sort, direction))
        .offset(page *SIZE)
        .limit(SIZE)
        .fetch();
  }

  private OrderSpecifier<?> createOrderSpecifier(
      String sort,
      String direction
  ) {
    Order order = parseOrderDirection(direction);
    QBoard board = QBoard.board;

    if (sort == null) {
      return new OrderSpecifier<>(Order.DESC, board.createTime);
    }
    //TODO 열거형으로 빼기
    return switch (sort.toLowerCase()) {
      case "title" -> new OrderSpecifier<>(order, board.title);
      case "view_count" -> new OrderSpecifier<>(order, board.viewCount);
      case "board_id" -> new OrderSpecifier<>(order, board.id);
      case "create_time" -> new OrderSpecifier<>(order, board.createTime);
      default -> new OrderSpecifier<>(Order.DESC, board.createTime);
    };
  }

  private Order parseOrderDirection(String direction) {
    if (direction == null) {
      return Order.DESC;
    }

    return direction.equalsIgnoreCase("asc") ? Order.ASC : Order.DESC;
  }

}