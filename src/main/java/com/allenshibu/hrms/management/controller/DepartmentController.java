package com.allenshibu.hrms.management.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.allenshibu.hrms.management.exception.DepartmentNotFoundException;
import com.allenshibu.hrms.management.model.Department;
import com.allenshibu.hrms.management.service.DepartmentService;

@RestController
@RequestMapping("/deparments")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable String id) {
        try {
            UUID departmentId = UUID.fromString(id);
            return ResponseEntity.ok(departmentService.getDepartmentById(departmentId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid UUID format");
        }
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
        try {
            UUID departmentId = UUID.fromString(id);
            return ResponseEntity.ok(departmentService.updateDepartment(departmentId, department));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid UUID format");
        }
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

    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<String> handleDepartmentNotFoundException(DepartmentNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
