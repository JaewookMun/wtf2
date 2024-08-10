package com.wtf2.erp.board.dto;

import com.wtf2.erp.board.domain.PageContent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TextLine {
    private Long id;
    private String line;
    private int index;

    @Builder
    public TextLine(PageContent pageContent) {
        this.id = pageContent.getId();
        this.line = pageContent.getLine();
        this.index = pageContent.getIndex();
    }
}
