package com.example.board.like.controller;

import com.example.board.like.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class LikeController {

  private final LikeService likeService;

  @PostMapping("/{id}/like")
  public ResponseEntity<?> like(@PathVariable("id") Long id) {
    likeService.doLike(id);

    return ResponseEntity.status(HttpStatus.CREATED).body(1L);
  }

  @GetMapping("{id}/like")
  public ResponseEntity<?> getLike(@PathVariable("id") Long id) {
    boolean isLike = likeService.isLike(id);

    return ResponseEntity.status(HttpStatus.CREATED).body(isLike);
  }

}