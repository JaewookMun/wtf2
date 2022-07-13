package personal.margin.wtf2.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter @Setter
@DynamicUpdate
public class Dept {

    @Id @GeneratedValue
    @Column (name = "dept_id")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Dept parent;

    private int displayOrder;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<Dept> children = new ArrayList<>();

    @OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
    private List<Employee> employees = new ArrayList<>();

    /**
     * 연관관계 메서드
     */
    public void setParent(Dept parent) {
        this.parent = parent;
        parent.getChildren().add(this);
    }


    // 생성 메서드
    public static Dept createDept(String name, @Nullable Dept parent) {
        Dept dept = new Dept();
        dept.setName(name);
        dept.setParent(parent);

        return dept;
    }
}
