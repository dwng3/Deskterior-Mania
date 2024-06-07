package com.example.DTM.service;

import com.example.DTM.domain.Member;
import com.example.DTM.dto.member.MemberResponseDTO;
import com.example.DTM.dto.member.MemberSignupDTO;
import com.example.DTM.dto.member.MemberUpdateDTO;

import java.util.List;

public interface MemberService {

    public Member singup(MemberSignupDTO dto);
    public List<MemberResponseDTO> getAllMembers();
    public MemberResponseDTO getMemberById(Long id);
    public void updateMember(Long id, MemberUpdateDTO dto);
    public void deleteMember(Long id);
}
