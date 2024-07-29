package com.wtf2.erp.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Content {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long id;

    @OneToOne(mappedBy = "content", fetch = FetchType.LAZY)
    private Board board;

    @Column(columnDefinition = "TEXT")
    private String text;

    public Content(String text) {
        this.text = text;
    }

    public void setBoard(Board board) {
        this.board = board;
    }
}
