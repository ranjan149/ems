package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employee-list")
    public String employeeList(Model model) {
        List<Employee> employeeList = this.employeeService.findAllEmployees();
        model.addAttribute("listEmployees", employeeList);
        return "employee-list";
    }

    @GetMapping("/employee-form")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "new-employee";
    }

    @PostMapping("/employee/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        System.out.println("Start Date: " + employee.getStartDate());
        this.employeeService.saveEmployee(employee);
        return "redirect:/employee-list";
    }

    @GetMapping("/employee-form/{id}")
    public String showUpdateEmployeeForm(@PathVariable long id, Model model) {
        Employee employee = this.employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update-employee";
    }

    @GetMapping("/employee-delete/{id}")
    public String deleteEmployee(@PathVariable long id) {
        this.employeeService.deleteEmployee(id);
        return "redirect:/employee-list";
    }

}
