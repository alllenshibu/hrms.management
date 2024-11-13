package com.allenshibu.hrms.employeemanagement.exception;

import java.util.UUID;

import lombok.Getter;

public class EmployeeNotFoundException extends RuntimeException {

    @Getter
    private UUID id;

    public EmployeeNotFoundException(UUID id) {
        super("Employee with id " + id + " not found");
    }
}
