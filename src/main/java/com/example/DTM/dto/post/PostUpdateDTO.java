package com.example.DTM.dto.post;

import com.example.DTM.domain.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostUpdateDTO {

    private String title;
    private String content;
    private List<Image> images;
}
