package com.example.DTM.domain;


import com.example.DTM.dto.MemberDTO;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Member extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false, nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phone;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @Builder
    public Member(String name, String password, String phone) {
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public void updateMember(MemberDTO dto) {
        this.password = dto.getPassword();
        this.phone = dto.getPhone();
    }

    public static Member toEntity(MemberDTO dto){
        return Member.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
    }
}
