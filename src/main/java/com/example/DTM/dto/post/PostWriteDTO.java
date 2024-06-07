package com.example.DTM.dto.post;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteDTO {

    private String title;
    private String content;
    private String imagePath;
    private Long memberId;
}
