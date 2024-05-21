package com.wtf2.erp.dept.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * name, parentId
 */
@Getter @Setter
public class DeptCreateDto {

    private String name;
    private Long parentId;
}
