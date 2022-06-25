package personal.margin.wtf2.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
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

}
