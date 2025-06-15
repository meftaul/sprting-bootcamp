package com.mislbd.webapp.controller;

import com.mislbd.webapp.entity.Employee;
import com.mislbd.webapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

//    @GetMapping(path = "/employees/{empId}")
//    public Employee getEmployee(@PathVariable(name = "empId") Long employeeId) {
////        return employeeService.getEmployeeById(employeeId);
//        return new Employee();
//    }

}

//http://localhost:8081/h2-console/test.do?jsessionid=2d6a7271d2985b06ebd035d40dbeeeb1&key=Val
//http://localhost:8081/employee/{employeeId}

// Convention:
// Employee:
//    - Get All (Pagination) -> /employees (GET)
//    - Get By Id -> /employees/{empId} (GET)
//    - Insert -> /employees (POST)
//    - Update -> /employees/{empId} (PUT)
//    - Delete -> /employees/{empId} (DELETE)