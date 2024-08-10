package com.wtf2.erp.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PageContent {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "page_content_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    private String line;
    private int index;

    public PageContent(String line) {
        this.line = line;
    }

    protected void setBoard(Board board) {
        this.board = board;
    }
}
