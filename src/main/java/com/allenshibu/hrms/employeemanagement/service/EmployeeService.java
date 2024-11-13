package com.allenshibu.hrms.employeemanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allenshibu.hrms.employeemanagement.model.Employee;
import com.allenshibu.hrms.employeemanagement.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}
