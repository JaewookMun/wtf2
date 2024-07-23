package com.wtf2.erp.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardRequestDto {

    private String type;
    private String title;
    private String content;

}
