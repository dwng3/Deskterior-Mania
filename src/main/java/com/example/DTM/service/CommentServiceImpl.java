package com.example.DTM.service;

import com.example.DTM.domain.Comment;
import com.example.DTM.domain.Member;
import com.example.DTM.domain.Post;
import com.example.DTM.dto.comment.CommentWriteDTO;
import com.example.DTM.repository.CommentRepository;
import com.example.DTM.repository.MemberRepository;
import com.example.DTM.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    public Comment writeComment(CommentWriteDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Post post = postRepository.findById(dto.getPostId())
                .orElseThrow(() -> new IllegalArgumentException("Post not found"));

        Comment comment = Comment.builder()
                .content(dto.getContent())
                .member(member)
                .post(post)
                .build();

        post.addComment(comment);

        return commentRepository.save(comment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
