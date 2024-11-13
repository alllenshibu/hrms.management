package com.allenshibu.hrms.employeemanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.allenshibu.hrms.employeemanagement.model.Employee;
import com.allenshibu.hrms.employeemanagement.service.EmployeeService;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/")
    public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee) {
        return ResponseEntity.ok(employeeService.addNewEmployee(employee));
    }
}
