package com.wtf2.erp.board.domain;

import com.wtf2.erp.company.domain.Company;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.util.Assert;

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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "content_id")
    private Content content;

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

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<PageContent> pageLines = new ArrayList<>();

    /*
     * Constructor
     */
    @Builder
    public Board(String title, BoardType type, Company company) {
        this.title = title;
        this.type = type;
        this.company = company;
    }

    /*
     * method
     */
    public Long addText(String text) {
        if (type.equals(BoardType.PAGE)) {
            PageContent pageContent = new PageContent(text);
            pageContent.setBoard(this);
            pageLines.add(pageContent);

            return pageContent.getId();
        }

        this.content = new Content(text);
        content.setBoard(this);

        return content.getId();
    }

//    public PageContent getNewPageLine(String textLine) {
//        PageContent pageContent = new PageContent(textLine);
//        pageContent.setBoard(this);
//
//        return pageContent;
//    }

    public void addChild(Board child) {
        children.add(child);
        child.setParent(this);
    }

    protected void setParent(Board parent) {
        this.parent = parent;
    }
}
