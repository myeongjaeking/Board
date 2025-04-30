package com.example.board.board.entity;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;

public enum Sort {
  TITLE {
    @Override
    public OrderSpecifier<?> getOrderSpecifier(Order order) {
      return new OrderSpecifier<>(order, QBoard.board.title);
    }
  },
  VIEW_COUNT {
    @Override
    public OrderSpecifier<?> getOrderSpecifier(Order order) {
      return new OrderSpecifier<>(order, QBoard.board.viewCount);
    }
  },
  BOARD_ID {
    @Override
    public OrderSpecifier<?> getOrderSpecifier(Order order) {
      return new OrderSpecifier<>(order, QBoard.board.id);
    }
  },
  CREATE_TIME {
    @Override
    public OrderSpecifier<?> getOrderSpecifier(Order order) {
      return new OrderSpecifier<>(order, QBoard.board.createTime);
    }
  };

  public abstract OrderSpecifier<?> getOrderSpecifier(Order order);

  public static Sort from(String value) {
    return switch (value.toLowerCase()) {
      case "title" -> TITLE;
      case "view_count" -> VIEW_COUNT;
      case "board_id" -> BOARD_ID;
      default -> CREATE_TIME;
    };
  }

}