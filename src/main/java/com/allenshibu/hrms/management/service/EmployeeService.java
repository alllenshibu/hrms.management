package com.allenshibu.hrms.management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allenshibu.hrms.management.exception.EmployeeNotFoundException;
import com.allenshibu.hrms.management.model.Employee;
import com.allenshibu.hrms.management.repository.EmployeeRepository;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    public Employee getEmployeeByEmail(String email) {
        Optional<Employee> employee = employeeRepository.findByEmail(email);

        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException(email);
        }
    }

    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(UUID id, Employee employee) {
        Optional<Employee> existingEmployee = employeeRepository.findById(id);

        if (existingEmployee.isPresent()) {
            Employee currentEmployee = existingEmployee.get();

            currentEmployee.setFirstName(employee.getFirstName());

            if (employee.getMiddleName() != null && !employee.getMiddleName().isEmpty()) {
                currentEmployee.setMiddleName(employee.getMiddleName());
            }

            currentEmployee.setLastName(employee.getLastName());
            currentEmployee.setEmail(employee.getEmail());

            if (employee.getAlternateEmails() != null && employee.getAlternateEmails().length != 0) {
                currentEmployee.setAlternateEmails(employee.getAlternateEmails());
            }

            return employeeRepository.save(currentEmployee);
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }

    public void deleteEmployee(UUID id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
        } else {
            throw new EmployeeNotFoundException(id);
        }
    }
}
