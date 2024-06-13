package com.example.DTM.dto.notice;

import com.example.DTM.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class NoticeWriteDTO {

    private String title;
    private String content;
    private Long memberId;
    private List<String> imagePaths;

}
