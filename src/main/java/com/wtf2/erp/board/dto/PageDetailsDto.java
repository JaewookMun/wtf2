package com.wtf2.erp.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@Getter @Setter
@Builder
public class PageDetailsDto {
    private String title;
    private List<TextLine> lines;
}
