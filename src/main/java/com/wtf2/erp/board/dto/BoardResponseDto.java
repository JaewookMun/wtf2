package com.wtf2.erp.board.dto;

import com.wtf2.erp.board.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private String createdDate;
    private String createdBy;
    private String lastModifiedDate;
    private int viewCount;

    public BoardResponseDto(Board board) {
        convertFrom(board);
    }

    public BoardResponseDto convertFrom(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.createdDate = board.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.createdBy = board.getCreatedBy();
        this.lastModifiedDate = board.getLastModifiedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.viewCount = board.getViewCount();

        return this;
    }
}
