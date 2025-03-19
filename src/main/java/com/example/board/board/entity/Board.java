package com.example.board.board.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "view_count")
    private int viewCount;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "create_time")
    private LocalDateTime createTime;

    @Builder(builderMethodName = "create")
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
        this.createTime = LocalDateTime.now();
        this.viewCount = 0;
        this.likeCount = 0;
    }

    public static BoardBuilder createBoard() {
        return Board.create();
    }

}