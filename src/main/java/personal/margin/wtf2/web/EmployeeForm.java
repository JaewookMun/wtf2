package personal.margin.wtf2.web;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import personal.margin.wtf2.domain.Authority;
import personal.margin.wtf2.domain.Dept;
import personal.margin.wtf2.domain.Employee;

@Getter @Setter
@NoArgsConstructor
public class EmployeeForm {
    private Long id;
    private String name;
    private String loginId;
    private String password;
    private String guid;
    private Dept dept;
    private Long deptId;
    private Authority authority;

    @Builder
    public EmployeeForm(Long id, String name, String loginId, String password, String guid, Dept dept, Long deptId, Authority authority) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.guid = guid;
        this.dept = dept;
        this.deptId = deptId;
        this.authority = authority;
    }

    public EmployeeForm buildForm(Employee employee) {
        return builder()
                .id(employee.getId())
                .name(employee.getName())
                .loginId(employee.getLoginId())
                .password(employee.getPassword())
                .dept(employee.getDept())
                .authority(employee.getAuthority())
                .build();
    }

}
