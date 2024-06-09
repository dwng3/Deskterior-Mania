package com.example.DTM.domain;


import com.example.DTM.dto.member.MemberUpdateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String role = MemberRole.MEMBER.getValue();

    @OneToMany
    @JoinColumn(name = "member_id")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String username, String password, String nickname, String phone) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.phone = phone;
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setMember(this);
    }

    public void removePost(Post post){
        posts.remove(post);
        post.setMember(null);
    }

    public void update(MemberUpdateDTO dto) {
        this.password = dto.getPassword();
        this.nickname = dto.getNickname();
        this.phone = dto.getPhone();
    }


}
