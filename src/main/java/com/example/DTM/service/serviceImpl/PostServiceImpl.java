package com.example.DTM.service.serviceImpl;

import com.example.DTM.domain.Category;
import com.example.DTM.domain.Image;
import com.example.DTM.domain.Member;
import com.example.DTM.domain.Post;
import com.example.DTM.dto.post.PostDetailDTO;
import com.example.DTM.dto.post.PostResponseDTO;
import com.example.DTM.dto.post.PostUpdateDTO;
import com.example.DTM.dto.post.PostWriteDTO;
import com.example.DTM.repository.ImageRepository;
import com.example.DTM.repository.MemberRepository;
import com.example.DTM.repository.PostRepository;
import com.example.DTM.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Transactional
@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final Path rootLocation = Paths.get("uploads");

    @Override
    public void writePost(PostWriteDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        try {
            Post post = Post.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .category(dto.getCategory())
                    .member(member)
                    .build();

            List<MultipartFile> images = dto.getImages();
            List<Image> imageEntities = new ArrayList<>();
            for (MultipartFile image : images) {
                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                }
                Path destinationFile = rootLocation.resolve(
                                Paths.get(image.getOriginalFilename()))
                        .normalize().toAbsolutePath();
                image.transferTo(destinationFile);

                // Image 엔티티 생성 및 저장
                Image imageEntity = new Image(destinationFile.toString(), post);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);
            postRepository.save(post);
            member.addPost(post);
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }

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

