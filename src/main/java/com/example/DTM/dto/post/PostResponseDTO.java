package com.example.DTM.dto.post;

import com.example.DTM.domain.Category;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {

    private String title;
    private String author;
    private Category category;
    private Long viewCount;

}
