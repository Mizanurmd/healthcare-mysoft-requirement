package com.mysoft.healthcare.controller;

import com.mysoft.healthcare.model.Employee;
import com.mysoft.healthcare.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee>createEmployee(
            @RequestParam("name")String name,
            @RequestParam("gender")String gender,
            @RequestParam("age")int age,
            @RequestParam("dob") Date dob,
            @RequestParam("phone")String phone,
            @RequestParam("email")String email,
            @RequestParam("address")String address,
            @RequestParam(value = "photo", required = true, defaultValue = "No image") MultipartFile photo

            ){
        Employee saveEmp = new Employee();
        saveEmp.setName(name);
        saveEmp.setGender(gender);
        saveEmp.setAge(age);
        saveEmp.setDob(dob);
        saveEmp.setPhone(phone);
        saveEmp.setEmail(email);
        saveEmp.setAddress(address);
        if (photo != null && !photo.isEmpty()) { // Only set pic if present
            try {
                saveEmp.setPhoto(photo.getBytes());
            }  catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        Employee save = employeeService.saveEmployee(saveEmp);
        return  new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Optional<Employee> getEmployee(@PathVariable("id")Long id){
        return employeeService.getEmployeeById(id);
    }

    @GetMapping
    public ResponseEntity<List<Employee>>allEmployee(){
        List<Employee>employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("gender") String gender,
            @RequestParam("age") int age,
            @RequestParam("dob") Date dob,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("address") String address,
            @RequestParam(value = "photo", required = false) MultipartFile photo) throws IOException {

        // Retrieve the existing employee from the database
        Employee existingEmployee = employeeService.getEmployeeById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + id));

        // Update the fields with the new values
        existingEmployee.setName(name);
        existingEmployee.setGender(gender);
        existingEmployee.setAge(age);
        existingEmployee.setDob(dob);
        existingEmployee.setPhone(phone);
        existingEmployee.setEmail(email);
        existingEmployee.setAddress(address);

        // Only update the photo if a new one is provided
        if (photo != null && !photo.isEmpty()) {
            existingEmployee.setPhoto(photo.getBytes());
        }

        // Save the updated employee
        Employee updatedEmployee = employeeService.updateEmployee(id, existingEmployee);

        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
