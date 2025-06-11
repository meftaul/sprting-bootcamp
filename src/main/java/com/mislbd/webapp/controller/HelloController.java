package com.mislbd.webapp.controller;

import com.mislbd.webapp.entity.Employee;
import com.mislbd.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(path = "/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping(path = "/insert-emp")
    public Long insertEmp() {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setFirstName("Meftaul");
        employee.setLastName("Haque");

        Long id = employeeService.insertEmployee(employee);

        return id;

    }

}
