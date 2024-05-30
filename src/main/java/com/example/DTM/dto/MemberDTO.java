package com.example.DTM.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    private String name;
    private String password;
    private String phone;

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class MemberSignupDTO extends MemberDTO{
    }

    @Getter
    @Setter
    public static class MemberUpdateDTO extends MemberDTO {
    }

}
