package com.example.DTM.service;

import com.example.DTM.domain.Member;
import com.example.DTM.dto.member.MemberResponseDTO;
import com.example.DTM.dto.member.MemberSignupDTO;
import com.example.DTM.dto.member.MemberUpdateDTO;
import com.example.DTM.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;


    @Override
    public Member singup(MemberSignupDTO dto) {
        if(memberRepository.existsByUsername(dto.getUsername())){
            throw new IllegalArgumentException("Username already exists");
        }

        Member member = Member.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .nickname(dto.getNickname())
                .build();

        return memberRepository.save(member);
    }

    @Override
    public List<MemberResponseDTO> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(member -> new MemberResponseDTO(member.getUsername(),member.getNickname()))
                .collect(Collectors.toList());
    }

    @Override
    public MemberResponseDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
        return MemberResponseDTO.builder()
                    .username(member.getUsername())
                    .nickname(member.getNickname())
                    .build();
    }

    @Override
    public void updateMember(Long id, MemberUpdateDTO dto) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found"));
            member.update(dto);
            memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
