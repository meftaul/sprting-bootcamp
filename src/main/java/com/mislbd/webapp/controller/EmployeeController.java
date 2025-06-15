package com.mislbd.webapp.controller;

import com.mislbd.webapp.dto.EmployeeDto;
import com.mislbd.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    // Employee:
//    - Get All (Pagination) -> /employees (GET)
//    - Get By Id -> /employees/{empId} (GET)
//    - Insert -> /employees (POST)
//    - Update -> /employees/{empId} (PUT)
//    - Delete -> /employees/{empId} (DELETE)

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    private List<EmployeeDto> getEmployees() {
//        return employeeService.getAllEmployees();
        return List.of(
                new EmployeeDto(1L, "Meftaul", "Haque"),
                new EmployeeDto(2L, "John", "Doe"),
                new EmployeeDto(3L, "Jane", "Smith")
        );
    }

    @GetMapping("/{empId}")
    private EmployeeDto getEmployeeById(Long empId) {
        // return employeeService.getEmployeeById(empId);
        return new EmployeeDto(empId, "Sample", "Employee");
    }

    @PostMapping
    private Long insertEmployee(@RequestBody EmployeeDto employeeDto) {
        // Convert EmployeeDto to Employee entity if needed
        // Employee employee = new Employee(employeeDto.getEmployeeId(), employeeDto.getFirstName(), employeeDto.getLastName());
        // return employeeService.insertEmployee(employee);
        return 1L; // Simulating insertion and returning the new employee ID
    }

}
