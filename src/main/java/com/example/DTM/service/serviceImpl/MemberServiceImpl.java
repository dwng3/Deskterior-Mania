package com.example.DTM.service.serviceImpl;

import com.example.DTM.domain.Member;
import com.example.DTM.domain.Post;
import com.example.DTM.dto.member.MemberResponseDTO;
import com.example.DTM.dto.member.MemberSignupDTO;
import com.example.DTM.dto.member.MemberUpdateDTO;
import com.example.DTM.dto.post.PostResponseDTO;
import com.example.DTM.repository.MemberRepository;
import com.example.DTM.repository.PostRepository;
import com.example.DTM.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PostRepository postRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Member singup(MemberSignupDTO dto) {
        if(memberRepository.existsByUsername(dto.getUsername())){
            throw new IllegalArgumentException("Username already exists");
        }
        Member member = Member.builder()
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
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

            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
            member.update(dto);
            memberRepository.save(member);
    }

    @Override
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PostResponseDTO> getPostsByMemberId(Long memberId) {
        List<Post> posts = postRepository.findByMemberId(memberId);
        return posts.stream()
                .map(post -> new PostResponseDTO(post.getTitle(),post.getMember().getNickname(),post.getCategory(),post.getViewCount()))
                .collect(Collectors.toList());
    }


//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Member member = memberRepository.findByUsername(username)
//                .orElseThrow(() -> new IllegalArgumentException("Post not found"));
//
//        return new org.springframework.security.core.userdetails.User(member.getUsername(), member.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(member.getRole())));
//    }
}
