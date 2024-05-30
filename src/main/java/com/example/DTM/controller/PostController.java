package com.example.DTM.controller;

import com.example.DTM.domain.Post;
import com.example.DTM.dto.PostDTO;
import com.example.DTM.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(){
        List<Post> postList = postService.getAllPosts();
        return ResponseEntity.ok(postList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Post>> getPostById(@PathVariable Long id){
        Optional<Post> post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDTO dto){
        postService.createPost(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post creation success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePost(@PathVariable Long id, @RequestBody PostDTO dto) {
        Optional<Post> optionalPost = postService.getPostById(id);
        Post post = optionalPost.get();
        post.updatePost(dto);

        return ResponseEntity.ok("update success");
    }

}
