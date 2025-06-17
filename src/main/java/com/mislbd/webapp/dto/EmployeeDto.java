package com.mislbd.webapp.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class EmployeeDto {

    @NotNull(message = "Employee ID cannot be null")
    private Long employeeId;

    @NotNull(message = "First name cannot be null")
    @Min(value = 3, message = "First name must be at least 1 character long")
    private String firstName;
    private String lastName;

    public EmployeeDto() {
    }

    public EmployeeDto(Long employeeId, String firstName, String lastName) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
