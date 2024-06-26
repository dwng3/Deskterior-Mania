package com.example.DTM.repository;

import com.example.DTM.domain.Post;
import com.example.DTM.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByMemberId(Long memberId);
    List<Post> findByCategory(Category category);
}
