package com.wtf2.erp.group.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GroupDto {
    private Long id;
    private String name;
    private String guid;

    public GroupDto(Long id, String name, String guid) {
        this.id = id;
        this.name = name;
        this.guid = guid;
    }
}
