package personal.margin.wtf2.web;

import lombok.Getter;
import lombok.Setter;
import personal.margin.wtf2.domain.Authority;
import personal.margin.wtf2.domain.Dept;

@Getter @Setter
public class EmployeeForm {
    private Long id;

    private String name;
    private String loginId;
    private String password;
    private String guid;
    private Dept dept;
    private Long deptId;
    private Authority authority;
}
