package com.wtf2.erp.board.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Board extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private int viewCount;

    @Enumerated(EnumType.STRING)
    private BoardType type;

    @OneToOne(mappedBy = "board", cascade = CascadeType.ALL)
    private Content content;

    @Builder
    public Board(String title, BoardType type) {
        this.title = title;
        this.type = type;
    }

    public void addContents(String text) {
        Content content = new Content(text);
        this.content = content;
        content.setBoard(this);
    }

}
