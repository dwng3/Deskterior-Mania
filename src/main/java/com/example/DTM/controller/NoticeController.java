package com.example.DTM.controller;

import com.example.DTM.dto.notice.NoticeDetailDTO;
import com.example.DTM.dto.notice.NoticeResponseDTO;
import com.example.DTM.dto.notice.NoticeWriteDTO;
import com.example.DTM.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/notices")
@RequiredArgsConstructor
@RestController
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping
    public ResponseEntity<String> writeNotice(@RequestBody NoticeWriteDTO dto) {
        noticeService.writeNotice(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Notice creation success");
    }

    @GetMapping
    public ResponseEntity<List<NoticeResponseDTO>> getAllNotices() {
        List<NoticeResponseDTO> notices = noticeService.getAllNotices();
        return ResponseEntity.ok(notices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoticeDetailDTO> getNoticeById(@PathVariable("id") Long id) {
        NoticeDetailDTO dto = noticeService.getDetailNotice(id);
        return ResponseEntity.ok(dto);
    }
}
