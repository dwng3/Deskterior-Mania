package com.example.DTM.service;

import com.example.DTM.domain.Post;
import com.example.DTM.dto.PostDTO;
import com.example.DTM.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public Post createPost(PostDTO dto) {
        Post post = Post.toEntity(dto);
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Long id) {
        return postRepository.findById(id);
    }

    @Override
    public Post updatePost(Long id, PostDTO dto) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if(optionalPost.isPresent()){
            Post post = optionalPost.get();
            post.updatePost(dto);
            return postRepository.save(post);
        } else {
            throw new IllegalArgumentException("게시물 없음");
        }
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
