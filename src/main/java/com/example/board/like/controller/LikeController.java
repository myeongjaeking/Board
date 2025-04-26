package com.example.board.like.controller;

import com.example.board.global.common.SecurityUtil;
import com.example.board.like.service.LikeLockService;
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
  private final LikeLockService likeLockService;

  @PostMapping("/{id}/like")
  public ResponseEntity<?> like(@PathVariable("id") Long id) {
    likeLockService.likeBoardLock(id);

    return ResponseEntity.noContent().build();
  }

  @GetMapping("{id}/like")
  public ResponseEntity<?> getLike(@PathVariable("id") Long id) {
    String nickname = SecurityUtil.getNickname();
    boolean isLike = likeService.isLike(nickname, id);

    return ResponseEntity.status(HttpStatus.OK).body(isLike);
  }

}