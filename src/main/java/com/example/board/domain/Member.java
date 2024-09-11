package com.example.board.domain;

import jakarta.persistence.*;

import java.nio.file.LinkOption;
import java.util.List;

@Entity
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "password")
    private String password;

}
