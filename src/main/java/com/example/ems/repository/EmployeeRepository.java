package com.example.ems.repository;

import com.example.ems.model.Employee;
import com.example.ems.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
