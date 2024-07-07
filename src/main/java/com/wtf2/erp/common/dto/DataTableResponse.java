package com.wtf2.erp.common.dto;

import lombok.*;

import java.util.List;

@Getter @Setter
@ToString
@AllArgsConstructor
@Builder
public class DataTableResponse<T> {

    private List<T> data;
    private int draw;

    /**
     * 나중에 뷰페이지에서 초기화해도 됨.
     */
    private int recordsTotal;
    private int recordsFiltered;
}