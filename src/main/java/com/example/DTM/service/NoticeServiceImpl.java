package com.example.DTM.service;

import com.example.DTM.domain.Notice;
import com.example.DTM.dto.notice.NoticeDetailDTO;
import com.example.DTM.dto.notice.NoticeResponseDTO;
import com.example.DTM.dto.notice.NoticeUpdateDTO;
import com.example.DTM.dto.notice.NoticeWriteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeServiceImpl implements NoticeService{
    @Override
    public Notice writeNotice(NoticeWriteDTO dto) {
        return null;
    }

    @Override
    public List<NoticeResponseDTO> getAllNotices() {
        return List.of();
    }

    @Override
    public NoticeDetailDTO getDetailNotice(Long id) {
        return null;
    }

    @Override
    public void updateNotice(Long id, NoticeUpdateDTO dto) {

    }

    @Override
    public void deleteNotice(Long id) {

    }
}
