package com.example.DTM.dto.member;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberUpdateDTO {

    String password;
    String nickname;
    String phone;
}
