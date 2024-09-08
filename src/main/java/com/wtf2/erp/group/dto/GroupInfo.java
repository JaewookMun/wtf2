package com.wtf2.erp.group.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GroupInfo {
    private Long id;
    private String name;

    public GroupInfo(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
