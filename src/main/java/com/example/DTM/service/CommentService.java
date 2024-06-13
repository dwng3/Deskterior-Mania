package com.example.DTM.service;

import com.example.DTM.domain.Comment;
import com.example.DTM.dto.comment.CommentWriteDTO;

public interface CommentService {

    public Comment writeComment(CommentWriteDTO dto);
    public void deleteComment(Long id);
}
