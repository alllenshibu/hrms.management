package com.allenshibu.hrms.management.exception;

import java.util.UUID;

import lombok.Getter;

public class EmployeeNotFoundException extends RuntimeException {

    @Getter
    private UUID id;

    @Getter
    private String email;

    public EmployeeNotFoundException() {
        super("Employee not found");
    }

    public EmployeeNotFoundException(UUID id) {
        super("Employee with id " + id + " not found");
    }

    public EmployeeNotFoundException(String email) {
        super("Employee with email " + email + " not found");
    }
}
