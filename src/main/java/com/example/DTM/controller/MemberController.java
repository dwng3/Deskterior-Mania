package com.example.DTM.controller;

import com.example.DTM.domain.Member;
import com.example.DTM.dto.MemberDTO;
import com.example.DTM.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        List<Member> memberList = memberService.getAllMembers();
        return ResponseEntity.ok(memberList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Member>> getMemberById(@PathVariable Long id){
        Optional<Member> member = memberService.getMemberById(id);
        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<String> signupMember(@RequestBody MemberDTO.MemberSignupDTO dto) {
        memberService.createMember(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member signup success");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMember(@PathVariable Long id, MemberDTO.MemberUpdateDTO dto) {
        memberService.updateMember(id,dto);
        return ResponseEntity.ok("Member update success");
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member delete success");
    }



}
