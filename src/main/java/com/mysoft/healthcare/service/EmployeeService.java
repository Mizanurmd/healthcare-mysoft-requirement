package com.mysoft.healthcare.service;

import com.mysoft.healthcare.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);

    Optional<Employee> getEmployeeById(Long id);

    List<Employee> getAllEmployee();

    Employee updateEmployee(long id, Employee employee);

    void deleteEmployee(Long id);
}
