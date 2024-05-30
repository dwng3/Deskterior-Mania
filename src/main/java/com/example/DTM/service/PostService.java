package com.example.DTM.service;

import com.example.DTM.domain.Post;
import com.example.DTM.dto.PostDTO;

import java.util.List;
import java.util.Optional;

public interface PostService {

    public Post createPost(PostDTO dto);
    public List<Post> getAllPosts();
    public Optional<Post> getPostById(Long id);
    public Post updatePost(Long id, PostDTO dto);
    public void deletePost(Long id);
}
