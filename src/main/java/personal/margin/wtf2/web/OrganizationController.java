package personal.margin.wtf2.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import personal.margin.wtf2.domain.Employee;
import personal.margin.wtf2.service.DeptService;
import personal.margin.wtf2.service.EmployeeService;

@Controller
@RequiredArgsConstructor
public class OrganizationController {

    private final EmployeeService employeeService;
    private final DeptService deptService;

    @GetMapping("/employees/new")
    public String addForm(Model model) {
        model.addAttribute("form", new EmployeeForm());

        return "employees/addEmployeeForm";
    }

    @PostMapping("/employees/new")
    public String add(EmployeeForm employeeForm) {
        Employee newEmployee = Employee.createEmployee(employeeForm);
        employeeService.join(newEmployee, employeeForm.getDeptId());

        return "redirect:/";
    }

    @GetMapping("/employees/{employeeId}/update")
    public String updateForm(@PathVariable Long employeeId, Model model) {
        Employee employee = employeeService.findOne(employeeId);
        EmployeeForm employeeForm = new EmployeeForm().buildForm(employee);
        model.addAttribute("employeeForm", employeeForm);

        return "employees/updateEmployeeForm";
    }

    @PostMapping("/employees/{employeeId}/update")
    public String update(@PathVariable Long employeeId, EmployeeForm employeeForm) {
        Employee employee = Employee.createEmployee(employeeForm);
        employeeService.update(employeeId, employee);

        return "redirect:/";
    }

    // Dept -> name, parent, displayOrder
    // 직원 전체 조회기능 관련 ...

}
