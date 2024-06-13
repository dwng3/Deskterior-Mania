package com.example.DTM.service;

import com.example.DTM.domain.Image;
import com.example.DTM.domain.Member;
import com.example.DTM.domain.Post;
import com.example.DTM.domain.Category;
import com.example.DTM.dto.post.PostDetailDTO;
import com.example.DTM.dto.post.PostResponseDTO;
import com.example.DTM.dto.post.PostUpdateDTO;
import com.example.DTM.dto.post.PostWriteDTO;
import com.example.DTM.repository.MemberRepository;
import com.example.DTM.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Transactional
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Override
    public Post writePost(PostWriteDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Post post = Post.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .category(dto.getCategory())
                .member(member)
                .build();

        for (String imagePath : dto.getImagePaths()) {
            Image image = new Image(imagePath);
            post.addImage(image);
        }
        member.addPost(post);

        return postRepository.save(post);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getAllPosts() {
        List<Post> posts  = postRepository.findAll();
        return posts.stream()
                .map(post -> new PostResponseDTO(post.getTitle(),post.getMember().getUsername(),post.getCategory(),post.getViewCount()))
                .collect(Collectors.toList());
    }

    @Override
    public List<PostResponseDTO> getPostsByCategory(Category category) {
        List<Post> posts =  postRepository.findByCategory(category);
        return posts.stream()
                .map(post -> new PostResponseDTO(post.getTitle(),post.getMember().getUsername(),post.getCategory(),post.getViewCount()))
                .collect(Collectors.toList());
    }

    @Override
    public PostDetailDTO getDetailPost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
        PostDetailDTO dto = PostDetailDTO.builder()
                .title(post.getTitle())
                .content(post.getContent())
                .images(post.getImages())
                .author(post.getMember().getNickname())
                .category(post.getCategory())
                .likeCount(post.getLikeCount())
                .comments(post.getComments())
                .build();

        return dto;
    }

    @Override
    public void updatePost(Long id, PostUpdateDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        post.update(dto);
    }

    @Override
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}

