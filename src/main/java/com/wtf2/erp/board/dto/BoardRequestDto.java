package com.wtf2.erp.board.dto;

import lombok.Data;

@Data
public class BoardRequestDto {

    private String type;
    private String title;
    private String content;

}
