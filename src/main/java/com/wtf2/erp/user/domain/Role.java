package com.wtf2.erp.user.domain;

import lombok.Getter;

@Getter
public enum Role {
    GENERAL("ROLE_GENERAL"),
    ADMIN("ROLE_ADMIN"),
    SUPER_ADMIN("ROLE_SUPER_ADMIN");

    private String authority;

    Role(String authority) {
        this.authority = authority;
    }
}
