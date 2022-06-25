package personal.margin.wtf2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Dept {

    @Id @GeneratedValue
    @Column (name = "dept_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Dept parent;

    private int displayOrder;

    @OneToMany(mappedBy = "parent")
    private List<Dept> children = new ArrayList<>();
}
