package com.example.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "create_board", nullable = false)
    private LocalDate createBoard;

    @Column(name = "view_counts", nullable = false)
    private int viewCount;

    @Column(name = "like_counts", nullable = false)
    private int likeCount;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder(builderMethodName = "create")
    public Board(String title, String content) {
        this.title = title;
        this.content = content;
        this.createBoard = LocalDate.now();
        this.viewCount = 0;
        this.likeCount = 0;
    }

    public void incrementViewCount() {
        this.viewCount++;
    }
    public void incrementLikeCount(){
        this.likeCount++;
    }
    public void update(String title,String content){
        this.title = title;
        this.content = content;
    }

}
