package com.wtf2.erp.dept.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class DeptEditDto {
    private String name;
    private Long parentId;
}
