package com.wtf2.erp.board.dto;

import com.wtf2.erp.board.domain.Board;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardResponseDto {

    private Long id;
    private String title;
    private LocalDateTime createdDate;
    private String createdBy;
    private LocalDateTime lastModifiedDate;

    public BoardResponseDto(Board board) {
        convertFrom(board);
    }

    public BoardResponseDto convertFrom(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.createdDate = board.getCreatedDate();
        this.createdBy = board.getCreatedBy();
        this.lastModifiedDate = board.getLastModifiedDate();

        return this;
    }
}
