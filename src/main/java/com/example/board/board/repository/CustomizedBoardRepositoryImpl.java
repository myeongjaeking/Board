package com.example.board.board.repository;

import static com.example.board.board.entity.QBoard.board;
import static com.example.board.board.entity.Sort.TITLE;
import static com.querydsl.core.types.Projections.constructor;

import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.board.entity.QBoard;
import com.example.board.board.entity.Sort;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class CustomizedBoardRepositoryImpl implements CustomizedBoardRepository {

  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public List<BoardGetResponse> getBoardList(
      Long boardId,
      String sort,
      String direction,
      int pageSize
  ) {
    BooleanBuilder dynamicLtId = new BooleanBuilder();

    if (boardId != null) {
      dynamicLtId.and(board.id.lt(boardId));
    }

    return jpaQueryFactory
        .select(
            constructor(BoardGetResponse.class,
                board.id,
                board.title,
                board.content,
                board.createTime,
                board.viewCount))
        .from(board)
        .where(dynamicLtId)
        .orderBy(createOrderSpecifier(sort, direction))
        .limit(pageSize)
        .fetch();
  }

  private OrderSpecifier<?> createOrderSpecifier(
      String sort,
      String direction
  ) {
    Order order = parseOrderDirection(direction);
    Sort sortEnum = Sort.from(sort);

    return sortEnum.getOrderSpecifier(order);
  }

  private Order parseOrderDirection(String direction) {
    if (direction == null) {
      return Order.DESC;
    }

    return direction.equalsIgnoreCase("asc") ? Order.ASC : Order.DESC;
  }

}