package com.example.DTM.domain;


import com.example.DTM.dto.member.MemberUpdateDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String nickname;

    private String phone;

    @OneToMany
    @JoinColumn(name = "member_id")
    private List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
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
