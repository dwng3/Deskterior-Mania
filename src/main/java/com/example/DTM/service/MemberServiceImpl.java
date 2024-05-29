package com.example.DTM.service;

import com.example.DTM.domain.Member;
import com.example.DTM.dto.MemberDTO;
import com.example.DTM.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    public Member createMember(MemberDTO.MemberSignupDTO dto) {
        if (memberRepository.existsByName(dto.getName())){
            throw new IllegalArgumentException("Member Id already exists");
        }
        Member member = Member.builder()
                            .name(dto.getName())
                            .password(dto.getPassword())
                            .phone(dto.getPhone())
                            .build();

        return memberRepository.save(member);
    }

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> getMemberById(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member updateMember(Long id, MemberDTO.MemberUpdateDTO dto) {
        Optional<Member> optionalMember = memberRepository.findById(id);
        if(optionalMember.isPresent()){
            Member member = optionalMember.get();
            member.updateMember(dto.getPassword(), dto.getPhone());
            return memberRepository.save(member);
        } else {
            throw new IllegalArgumentException("Member not exists");
        }
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
}
