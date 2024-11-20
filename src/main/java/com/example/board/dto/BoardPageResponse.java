package com.example.board.dto;

import lombok.Builder;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
@Builder
public record BoardPageResponse (
        String title,
        Long id,
        LocalDate createBoard,
        int viewCount
){
}
