package com.wtf2.erp.board.domain;

import com.wtf2.erp.company.domain.Company;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(exclude = {"children"})
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private int viewCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Enumerated(EnumType.STRING)
    private BoardType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Board parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Board> children = new ArrayList<>();

    /*
     * Constructor
     */
    @Builder
    public Board(String title, BoardType type) {
        this.title = title;
        this.type = type;
    }

    /*
     * method
     */
    public void addContent(String text) {
        Content content = new Content(text);
        content.setBoard(this);
    }

    public void addChild(Board child) {
        children.add(child);
        child.setParent(this);
    }

    protected void setParent(Board parent) {
        this.parent = parent;
    }
}
