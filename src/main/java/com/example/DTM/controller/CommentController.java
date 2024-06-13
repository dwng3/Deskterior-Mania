package com.example.DTM.controller;

import com.example.DTM.dto.comment.CommentWriteDTO;
import com.example.DTM.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/comments")
@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<String> writeComment(@RequestBody CommentWriteDTO dto) {
        commentService.writeComment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Comment creation success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
        return ResponseEntity.ok("Delete success");
    }
}
