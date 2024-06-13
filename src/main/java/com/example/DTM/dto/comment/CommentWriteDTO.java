package com.example.DTM.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CommentWriteDTO {

    private String content;
    private Long memberId;
    private Long postId;

}
