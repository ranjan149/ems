package com.example.ems.service;

import com.example.ems.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAllEmployees();

    void saveEmployee(Employee employee);

    Employee getEmployeeById(long id);

    void deleteEmployee(long id);
}
