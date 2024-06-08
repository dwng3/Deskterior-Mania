package com.example.DTM.controller;

import com.example.DTM.dto.post.PostDetailDTO;
import com.example.DTM.dto.post.PostResponseDTO;
import com.example.DTM.dto.post.PostUpdateDTO;
import com.example.DTM.dto.post.PostWriteDTO;
import com.example.DTM.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> writePost(@RequestBody PostWriteDTO dto) {
        postService.writePost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post creation success");
    }

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPosts() {
        List<PostResponseDTO> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/{memberId}/{id}")
    public ResponseEntity<PostDetailDTO> getPostDetail(@PathVariable("id")Long id) {
        PostDetailDTO post = postService.getDetailPost(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable("id")Long id, @RequestBody PostUpdateDTO dto) {
        postService.updatePost(id,dto);
        return ResponseEntity.ok("Update Success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id")Long id) {
        postService.deletePost(id);
        return ResponseEntity.ok("Delete Success");
    }

}
