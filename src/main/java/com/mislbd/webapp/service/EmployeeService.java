package com.mislbd.webapp.service;

import com.mislbd.webapp.entity.Employee;
import com.mislbd.webapp.repsitory.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Long insertEmployee(Employee employee) {
        Employee newEmployee = employeeRepository.save(employee);
        return newEmployee.getEmployeeId();
    }

    public Page<Employee> getAllEmployees(Pageable pageable) {

        Page<Employee> employeeList = employeeRepository.findAll(pageable);
        return employeeList;
    }
}
