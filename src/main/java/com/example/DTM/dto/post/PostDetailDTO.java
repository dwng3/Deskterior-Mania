package com.example.DTM.dto.post;

import com.example.DTM.domain.Category;
import com.example.DTM.domain.Comment;
import com.example.DTM.domain.Image;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDetailDTO {

    private String title;
    private String content;
    private List<Image> images;
    private String author;
    private Category category;
    private Long likeCount;
    private List<Comment> comments;

}
