package com.example.board.bookmark.controller;

import com.example.board.board.dto.response.BoardGetResponse;
import com.example.board.bookmark.service.BookmarkService;
import java.util.List;
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
public class BookmarkController {

  private final BookmarkService bookmarkService;

  @PostMapping("/{id}/bookmark")
  public ResponseEntity<?> register(@PathVariable("id") Long id) {
    bookmarkService.doRegister(id);

    return ResponseEntity.status(HttpStatus.CREATED).body(1);
  }

  @GetMapping("/{id}/bookmark")
  public ResponseEntity<Boolean> getRegister(@PathVariable("id") Long id) {
    boolean isRegister = bookmarkService.isRegister(id);

    return ResponseEntity.status(HttpStatus.OK).body(isRegister);
  }

  @GetMapping("/bookmark")
  public ResponseEntity<List<BoardGetResponse>> getBookmark() {
    List<BoardGetResponse> bookmarks = bookmarkService.getBookmarks();

    return ResponseEntity.status(HttpStatus.OK).body(bookmarks);
  }

}