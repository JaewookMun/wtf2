package personal.margin.wtf2.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import personal.margin.wtf2.domain.Employee;
import personal.margin.wtf2.repository.EmployeeRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public Long join(Employee employee) {

        validateDuplicateEmployee(employee);
        employeeRepository.save(employee);

        return employee.getId();
    }

    private void validateDuplicateEmployee(Employee employee) {

        List<Employee> findByGUID = employeeRepository.findByGUID(employee.getGuid());
        if(findByGUID.size() > 0) {
            throw new IllegalStateException(employee.getName() + " is already existing employee");
        }
    }

    @Transactional
    public Long update(Long id, Employee employee) {

        Employee findOne = employeeRepository.findOne(id);

        findOne.setName(employee.getName());
        findOne.setPassword(employee.getPassword());
        findOne.setAuthority(employee.getAuthority());
        findOne.setDept(employee.getDept());

        return id;
    }

    public List<Employee> findEmployees() {
        return employeeRepository.findAll();
    }

}
