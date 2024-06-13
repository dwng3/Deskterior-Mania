package com.example.DTM.dto.notice;

import com.example.DTM.domain.Image;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NoticeDetailDTO {

    private String title;
    private String content;
    private List<Image> images;
    private String author;

}
