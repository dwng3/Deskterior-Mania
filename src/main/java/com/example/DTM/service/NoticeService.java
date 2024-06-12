package com.example.DTM.service;

import com.example.DTM.domain.Notice;
import com.example.DTM.domain.Post;
import com.example.DTM.dto.notice.NoticeDetailDTO;
import com.example.DTM.dto.notice.NoticeResponseDTO;
import com.example.DTM.dto.notice.NoticeUpdateDTO;
import com.example.DTM.dto.notice.NoticeWriteDTO;
import com.example.DTM.dto.post.PostDetailDTO;
import com.example.DTM.dto.post.PostResponseDTO;
import com.example.DTM.dto.post.PostUpdateDTO;
import com.example.DTM.dto.post.PostWriteDTO;

import java.util.List;

public interface NoticeService {

    public Notice writeNotice(NoticeWriteDTO dto);
    public List<NoticeResponseDTO> getAllNotices();
    public NoticeDetailDTO getDetailNotice(Long id);
    public void updateNotice(Long id, NoticeUpdateDTO dto);
    public void deleteNotice(Long id);

}
