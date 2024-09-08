package com.wtf2.erp.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {

    private String type;
    private String title;
    private String content;
    private Long groupId;

}
