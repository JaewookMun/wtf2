package personal.margin.wtf2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee writer;

    private String title;
    private String content;
    private LocalDateTime postDate;

    @OneToMany(mappedBy = "board")
    private List<File> files = new ArrayList<>();

    @OneToMany(mappedBy = "board")
    private List<Reply> replies = new ArrayList<>();

    /**
     * 연관관계 메서드
     */
    public void addFile(File file) {
        files.add(file);
        file.setBoard(this);
    }

    /**
     * 연관관계 메서드
     */
    public void addReply(Reply reply) {
        replies.add(reply);
        reply.setBoard(this);
    }

}
