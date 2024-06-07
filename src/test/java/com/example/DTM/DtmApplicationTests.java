package com.example.DTM;

import com.example.DTM.domain.Member;
import com.example.DTM.repository.MemberRepository;
import com.example.DTM.repository.PostRepository;
import com.example.DTM.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class DtmApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberService memberService;





}
