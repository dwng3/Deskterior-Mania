package com.example.DTM.service;

import com.example.DTM.domain.Member;
import com.example.DTM.dto.MemberDTO;
import com.example.DTM.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    public Member createMember(MemberDTO.MemberSignupDTO dto);
    public List<Member> getAllMembers();
    public Optional<Member> getMemberById(Long id);
    public Member updateMember(Long id, MemberDTO.MemberUpdateDTO dto);
    public void deleteMember(Long id);
}
