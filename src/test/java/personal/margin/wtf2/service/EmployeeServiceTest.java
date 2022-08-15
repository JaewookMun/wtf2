package personal.margin.wtf2.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.Dept;
import personal.margin.wtf2.domain.Employee;
import personal.margin.wtf2.repository.EmployeeRepository;
import personal.margin.wtf2.web.EmployeeForm;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;
    @PersistenceContext
    private EntityManager em;


    @Test
    @Rollback(value = false)
    public void 직원추가() {
        //given
        EmployeeForm employeeForm = new EmployeeForm();
        employeeForm.setName("James");
        employeeForm.setLoginId("jm123@gmail.com");

        Long deptId = null;
        List<Dept> list = em.createQuery("select d from Dept d where d.parent is null", Dept.class)
                .getResultList();

        for (Dept dept : list) {
            deptId = dept.getId();
        }

        //when
        Employee employee = Employee.createEmployee(employeeForm);

        Long saveId = employeeService.join(employee, deptId);
        System.out.println("saveId = " + saveId);

        //then
        assertThat(employeeRepository.findOne(saveId)).isEqualTo(employee);
    }

//    @Test
//    public void 직원정보_변경() {
//        //given
//        EmployeeForm employeeForm1 = new EmployeeForm();
//        employeeForm1.setName("James");
//
//        Employee original = Employee.createEmployee(employeeForm1);
//
//        Long saveId = employeeService.join(original, null);
//
//
//        Employee changedEmp = new Employee();
////        changedEmp.setAuthority(Authority.USER);
////        changedEmp.setName("James");
//
//        //when
//        employeeService.update(saveId, changedEmp);
//
//        //then
//        Employee one = employeeRepository.findOne(saveId);
//        System.out.println("one's authority = " + one.getAuthority());
//
//        assertThat(employeeRepository.findOne(saveId).getAuthority()).isNotNull();
//    }

}