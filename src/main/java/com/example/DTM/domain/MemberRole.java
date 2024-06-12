package com.example.DTM.domain;

import lombok.Getter;

@Getter
public enum MemberRole {
    ADMIN("ROLE_ADMIN"),
    MEMBER("ROLE_USER");

    MemberRole(String value) {
        this.value = value;
    }

    private String value;
}
