package com.example.DTM.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostDTO {

    private String title;
    private String content;
    private String imagePath;

}
