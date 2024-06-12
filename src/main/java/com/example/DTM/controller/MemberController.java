package com.example.DTM.controller;

import com.example.DTM.dto.member.MemberResponseDTO;
import com.example.DTM.dto.member.MemberSignupDTO;
import com.example.DTM.dto.member.MemberUpdateDTO;
import com.example.DTM.dto.post.PostResponseDTO;
import com.example.DTM.service.MemberService;
import com.example.DTM.service.PostService;
import com.example.DTM.validator.SignupValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/api/members")
@RestController
public class MemberController {

    private final MemberService memberService;
    private final PostService postService;
    private final SignupValidator signupValidator;

    @PostMapping
    public ResponseEntity<String> signup(@Valid @RequestBody MemberSignupDTO dto, BindingResult result) {
        signupValidator.validate(dto,result);
        if(result.hasErrors()){
            StringBuilder errorMessage = new StringBuilder();
            for (ObjectError error : result.getAllErrors()) {
                errorMessage.append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(errorMessage.toString());
        }
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
