package com.example.DTM.dto.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberSignupDTO {

    private String username;
    private String password;
    private String nickname;
    private String phone;
}
