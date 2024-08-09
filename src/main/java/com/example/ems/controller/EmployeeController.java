package com.example.ems.controller;

import com.example.ems.model.Employee;
import com.example.ems.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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


}
