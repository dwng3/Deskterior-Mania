package com.example.DTM.controller;

import com.example.DTM.dto.member.MemberResponseDTO;
import com.example.DTM.dto.member.MemberSignupDTO;
import com.example.DTM.dto.member.MemberUpdateDTO;
import com.example.DTM.dto.post.PostResponseDTO;
import com.example.DTM.service.MemberService;
import com.example.DTM.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> signup(@RequestBody MemberSignupDTO dto) {
        memberService.singup(dto);
        return ResponseEntity.ok("Signup success");
    }

    @GetMapping
    public ResponseEntity<List<MemberResponseDTO>> getAllMembers() {
        List<MemberResponseDTO> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDTO> getMemberByMemberId(@PathVariable("id") Long id) {
        MemberResponseDTO member =  memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable("id") Long id, @RequestBody MemberUpdateDTO dto) {
        memberService.updateMember(id, dto);
        return ResponseEntity.ok("update success");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable("id") Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("delete success");
    }

    @GetMapping("/{memberId}/posts")
    public ResponseEntity<List<PostResponseDTO>> getPostsByMemberId(@PathVariable("memberId")Long memberId) {
        List<PostResponseDTO> posts = memberService.getPostsByMemberId(memberId);
        return ResponseEntity.ok(posts);
    }

}
