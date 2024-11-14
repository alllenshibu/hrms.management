package com.allenshibu.hrms.management.exception;

import java.util.UUID;

import lombok.Getter;

public class DepartmentNotFoundException extends RuntimeException {

    @Getter
    private UUID id;

    @Getter
    private String departmentId;

    public DepartmentNotFoundException() {
        super("Department not found");
    }

    public DepartmentNotFoundException(UUID id) {
        super("Department with id " + id + " not found");
    }

    public DepartmentNotFoundException(String departmentId) {
        super("Department with departmentId " + departmentId + " not found");
    }
}
