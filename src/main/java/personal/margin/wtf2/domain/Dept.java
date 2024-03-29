package personal.margin.wtf2.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter//(value = AccessLevel.PROTECTED)
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
    private List<Dept> childList = new ArrayList<>();

    @OneToMany(mappedBy = "dept", cascade = CascadeType.PERSIST)
    private List<Employee> employees = new ArrayList<>();

    public static Dept createRootDept(String name) {
        Dept rootDept = new Dept();
        rootDept.setName(name);

        return rootDept;
    }

    // 생성 메서드
    public static Dept createDept(String name, Dept parent) {
        Dept dept = new Dept();
        dept.setName(name);
        dept.setParent(parent);

        return dept;
    }

    /**
     * 연관관계 메서드
     */
    public void setParent(Dept parent) {
        this.parent = parent;
        parent.getChildList().add(this);
    }

}
