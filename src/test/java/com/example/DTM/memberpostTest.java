package com.example.DTM;


import com.example.DTM.domain.Member;
import com.example.DTM.domain.Post;
import com.example.DTM.repository.MemberRepository;
import com.example.DTM.repository.PostRepository;
import com.example.DTM.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class memberpostTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void 멤버와포스트() {
        Member member = Member.builder()
                .name("asd")
                .password("sdd")
                .phone("ssss")
                .build();

        Post post = new Post();
        post.setTitle("dd");
        post.setContent("sddsd");
        post.setImagePath("sdds");
        post.setAuthor(member);



    }
}
