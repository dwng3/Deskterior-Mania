package com.example.DTM.service;

import com.example.DTM.domain.Post;
import com.example.DTM.domain.Category;
import com.example.DTM.dto.post.PostDetailDTO;
import com.example.DTM.dto.post.PostResponseDTO;
import com.example.DTM.dto.post.PostUpdateDTO;
import com.example.DTM.dto.post.PostWriteDTO;

import java.util.List;

public interface PostService {
    public Post writePost(PostWriteDTO dto);
    public List<PostResponseDTO> getAllPosts();
    public List<PostResponseDTO> getPostsByCategory(Category category);
    public PostDetailDTO getDetailPost(Long id);
    public void updatePost(Long id, PostUpdateDTO dto);
    public void deletePost(Long id);

}
