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
    private int recordsTotal;
    private int recordsFiltered;
}