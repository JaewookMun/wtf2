package personal.margin.wtf2.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import personal.margin.wtf2.domain.Employee;
import personal.margin.wtf2.service.EmployeeService;

@Controller
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

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
}
