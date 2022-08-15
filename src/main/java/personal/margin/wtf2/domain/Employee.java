package personal.margin.wtf2.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import personal.margin.wtf2.web.EmployeeForm;

import javax.persistence.*;

@Entity
@Getter //@Setter
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

    public static Employee createEmployee(EmployeeForm form) {
        Employee employee = new Employee();
        employee.name = form.getName();
        employee.loginId = form.getLoginId();
        employee.password = form.getPassword();
        employee.guid = form.getGuid();

//        employee.setName(form.getName());
//        employee.setLoginId(form.getLoginId());
//        employee.setPassword(form.getPassword());
//        employee.setGuid(form.getGuid());

        return employee;
    }

    public void update(String name, String password, Authority authority) {
        this.name = name;
        this.password = password;
        this.authority = authority;
    }
}
