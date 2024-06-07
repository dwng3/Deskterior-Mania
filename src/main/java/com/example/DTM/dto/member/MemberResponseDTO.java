package com.example.DTM.dto.member;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MemberResponseDTO {

    private String username;
    private String nickname;
}
