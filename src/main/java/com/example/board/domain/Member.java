package com.example.board.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nickname", unique = true)
    private String nickname;
    @Column(name = "password", nullable = false)
    private String password;

}
