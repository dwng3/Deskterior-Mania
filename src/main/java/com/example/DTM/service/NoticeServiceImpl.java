package com.example.DTM.service;

import com.example.DTM.domain.Image;
import com.example.DTM.domain.Member;
import com.example.DTM.domain.Notice;
import com.example.DTM.domain.Post;
import com.example.DTM.dto.notice.NoticeDetailDTO;
import com.example.DTM.dto.notice.NoticeResponseDTO;
import com.example.DTM.dto.notice.NoticeUpdateDTO;
import com.example.DTM.dto.notice.NoticeWriteDTO;
import com.example.DTM.dto.post.PostResponseDTO;
import com.example.DTM.repository.MemberRepository;
import com.example.DTM.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService{

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Notice writeNotice(NoticeWriteDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        Notice notice = Notice.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .member(member)
                .build();

        for (String imagePath : dto.getImagePaths()) {
            Image image = new Image(imagePath);
            notice.addImage(image);
        }
        member.addNotice(notice);

        return noticeRepository.save(notice);
    }

    @Override
    public List<NoticeResponseDTO> getAllNotices() {
        List<Notice> notices = noticeRepository.findAll();
        return notices.stream()
                .map(notice -> new NoticeResponseDTO(notice.getTitle(),notice.getMember().getUsername()))
                .collect(Collectors.toList());
    }

    @Override
    public NoticeDetailDTO getDetailNotice(Long id) {
        Notice notice = noticeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Notice not found"));

        NoticeDetailDTO dto = NoticeDetailDTO.builder()
                .title(notice.getTitle())
                .content(notice.getContent())
                .author(notice.getMember().getNickname())
                .images(notice.getImages())
                .build();

        return dto;
    }

    @Override
    public void updateNotice(Long id, NoticeUpdateDTO dto) {

    }

    @Override
    public void deleteNotice(Long id) {

    }
}
