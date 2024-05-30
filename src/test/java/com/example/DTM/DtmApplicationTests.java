package com.example.DTM;

import com.example.DTM.domain.Member;
import com.example.DTM.domain.Post;
import com.example.DTM.repository.MemberRepository;
import com.example.DTM.repository.PostRepository;
import com.example.DTM.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DtmApplicationTests {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private MemberService memberService;

    @Test
    void 멤버와포스트() {
        Member member = Member.builder()
                .name("asd")
                .password("sdd")
                .phone("ssss")
                .build();

        memberService.createMember(member);

        Post post = new Post();
        post.setTitle("dd");
        post.setContent("sddsd");
        post.setImagePath("sdds");
        post.setAuthor(member);



    }


}
