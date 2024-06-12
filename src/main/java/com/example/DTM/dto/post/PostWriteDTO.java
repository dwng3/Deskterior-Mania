package com.example.DTM.dto.post;

import com.example.DTM.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteDTO {

    private String title;
    private String content;
    private Category category;
    private Long memberId;
    private List<String> imagePaths;
}
