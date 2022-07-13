package personal.margin.wtf2.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@Getter @Setter
@DynamicUpdate
public class Employee {

    @Id @GeneratedValue
    @Column(name = "employee_id")
    private Long id;

    private String name;
    private String loginId;
    private String password;
    private String guid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dept_id")
    private Dept dept;

    @Enumerated(EnumType.STRING)
    private Authority authority;


    // 연관관계 편의 메서드
    public void setDept(Dept dept) {
        this.dept = dept;
        dept.getEmployees().add(this);
    }
}
