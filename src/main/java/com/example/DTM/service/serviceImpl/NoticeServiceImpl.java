package com.example.DTM.service.serviceImpl;

import com.example.DTM.domain.Image;
import com.example.DTM.domain.Member;
import com.example.DTM.domain.Notice;
import com.example.DTM.dto.notice.NoticeDetailDTO;
import com.example.DTM.dto.notice.NoticeResponseDTO;
import com.example.DTM.dto.notice.NoticeUpdateDTO;
import com.example.DTM.dto.notice.NoticeWriteDTO;
import com.example.DTM.repository.ImageRepository;
import com.example.DTM.repository.MemberRepository;
import com.example.DTM.repository.NoticeRepository;
import com.example.DTM.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;
    private final ImageRepository imageRepository;
    private final Path rootLocation = Paths.get("uploads");

    @Override
    public void writeNotice(NoticeWriteDTO dto) {
        Member member = memberRepository.findById(dto.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));

        try {
            Notice notice = Notice.builder()
                    .title(dto.getTitle())
                    .content(dto.getContent())
                    .member(member)
                    .build();

            List<MultipartFile> images = dto.getImages();
            List<Image> imageEntities = new ArrayList<>();
            for (MultipartFile image : images) {
                if (!Files.exists(rootLocation)) {
                    Files.createDirectories(rootLocation);
                }
                Path destinationFile = rootLocation.resolve(
                                Paths.get(image.getOriginalFilename()))
                        .normalize().toAbsolutePath();
                image.transferTo(destinationFile);

                // Image 엔티티 생성 및 저장
                Image imageEntity = new Image(destinationFile.toString(), notice);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);
            member.addNotice(notice);
            noticeRepository.save(notice);
        } catch (Exception e) {
            throw new RuntimeException("Failed to store file", e);
        }
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
