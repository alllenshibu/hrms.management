package com.allenshibu.hrms.management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allenshibu.hrms.management.exception.DepartmentNotFoundException;
import com.allenshibu.hrms.management.exception.EmployeeNotFoundException;
import com.allenshibu.hrms.management.model.Department;
import com.allenshibu.hrms.management.service.DepartmentService;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable String id) {
        UUID departmentId = UUID.fromString(id);
        return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));

    }

    @GetMapping("/department-id/{departmentId}")
    public ResponseEntity<?> getDepartmentByDepartmentId(@PathVariable String departmentId) {
        return ResponseEntity.ok(departmentService.getDepartmentByDepartmentId(departmentId));
    }

    @PostMapping("/")
    public ResponseEntity<Department> addNewDepartment(@RequestBody Department department) {
        return ResponseEntity.ok(departmentService.addNewDepartment(department));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable String id, @RequestBody Department department) {
        UUID departmentId = UUID.fromString(id);
        return ResponseEntity.ok(departmentService.updateDepartment(departmentId, department));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable String id) {
        try {
            UUID departmentId = UUID.fromString(id);
            departmentService.deleteDepartment(departmentId);
            return ResponseEntity.ok("Department deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
    }

    @PostMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<?> addEmployeeToDepartment(@PathVariable String departmentId, @PathVariable String employeeId) {
        UUID departmentUUID = UUID.fromString(departmentId);
        UUID employeeUUID = UUID.fromString(employeeId);
        departmentService.addEmployeeToDepartment(departmentUUID, employeeUUID);
        return ResponseEntity.ok("Employee added to department successfully");
    }

    @DeleteMapping("/{departmentId}/employees/{employeeId}")
    public ResponseEntity<?> removeEmployeeFromDepartment(@PathVariable String departmentId, @PathVariable String employeeId) {
        UUID departmentUUID = UUID.fromString(departmentId);
        UUID employeeUUID = UUID.fromString(employeeId);
        departmentService.removeEmployeeFromDepartment(departmentUUID, employeeUUID);
        return ResponseEntity.ok("Employee removed from department successfully");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFoundException(DepartmentNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleEmployeeNotFoundException(EmployeeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
