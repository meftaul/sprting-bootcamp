package com.mislbd.webapp.controller;

import com.mislbd.webapp.dto.EmployeeDto;
import com.mislbd.webapp.entity.Employee;
import com.mislbd.webapp.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    // Employee:
//    - Get All (Pagination) -> /employees (GET)
//    - Get By Id -> /employees/{empId} (GET)
//    - Insert -> /employees (POST)
//    - Update -> /employees/{empId} (PUT)
//    - Delete -> /employees/{empId} (DELETE)
    // /employees/bulk-upload/ (POST) - Bulk Insert

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    private Page<Employee> getEmployees(
            Pageable pageable
    ) {
        Page<Employee> employeeList = employeeService.getAllEmployees(pageable);

        return employeeList;
    }

    @GetMapping("/{employeeId}")
    private ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "employeeId") Long empId) {
        // return employeeService.getEmployeeById(empId);
        EmployeeDto employee = new EmployeeDto(empId, "Sample", "Employee");

        if (Objects.isNull(employee)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @PostMapping
    private Long insertEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        // Convert EmployeeDto to Employee entity if needed
        // Employee employee = new Employee(employeeDto.getEmployeeId(), employeeDto.getFirstName(), employeeDto.getLastName());
        // return employeeService.insertEmployee(employee);
        return 1L; // Simulating insertion and returning the new employee ID
    }

}
