package com.example.DTM.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDTO {

    @Getter
    @Setter
    @AllArgsConstructor
    @Builder
    public static class MemberSignupDTO{
        private String name;
        private String password;
        private String phone;
    }

    @Getter
    @Setter
    public static class MemberUpdateDTO{
        private String password;
        private String phone;
    }

}
