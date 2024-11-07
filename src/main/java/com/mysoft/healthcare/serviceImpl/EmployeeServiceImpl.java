package com.mysoft.healthcare.serviceImpl;

import com.mysoft.healthcare.model.Employee;
import com.mysoft.healthcare.repository.EmployeeRepository;
import com.mysoft.healthcare.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return Optional.ofNullable(employeeRepository.findById(id).orElseThrow(() -> new RuntimeException(id + " is not found!!!")));
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee updateEmployee(long id, Employee employee) {
        Employee emp = employeeRepository.findById(id).get();
        emp.setName(employee.getName());
        emp.setGender(employee.getGender());
        emp.setAge(employee.getAge());
        emp.setDob(employee.getDob());
        emp.setPhone(employee.getPhone());
        emp.setEmail(employee.getEmail());
        emp.setAddress(employee.getAddress());
        emp.setPhoto(employee.getPhoto());


        return employeeRepository.save(emp);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }


}
