package personal.margin.wtf2.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.Authority;
import personal.margin.wtf2.domain.Employee;
import personal.margin.wtf2.repository.EmployeeRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class EmployeeServiceTest {

    @Autowired
    private EmployeeService empService;
    @Autowired
    private EmployeeRepository empRepository;


    @Test
//    @Rollback(value = false)
    public void 직원추가() {
        //given
        Employee employee = new Employee();
        employee.setName("James");

        //when
        Long saveId = empService.join(employee);

        //then
        assertThat(empRepository.findOne(saveId)).isEqualTo(employee);
    }

    @Test
    public void 직원정보_변경() {
        //given
        Employee original = new Employee();
        original.setName("James");

        Long saveId = empService.join(original);

        Employee changedEmp = new Employee();
        changedEmp.setAuthority(Authority.USER);
        changedEmp.setName("James");

        //when
        empService.update(saveId, changedEmp);

        //then
        Employee one = empRepository.findOne(saveId);
        System.out.println("one's authority = " + one.getAuthority());

        assertThat(empRepository.findOne(saveId).getAuthority()).isNotNull();
    }

}