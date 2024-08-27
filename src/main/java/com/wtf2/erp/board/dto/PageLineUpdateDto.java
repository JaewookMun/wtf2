package com.wtf2.erp.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Builder
@Getter @Setter
public class PageLineUpdateDto {
    private Long boardId;
    private Long lineId;
    private String lineContent;
}