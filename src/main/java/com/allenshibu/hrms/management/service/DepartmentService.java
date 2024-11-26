package com.allenshibu.hrms.management.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.allenshibu.hrms.management.exception.DepartmentNotFoundException;
import com.allenshibu.hrms.management.exception.EmployeeNotFoundException;
import com.allenshibu.hrms.management.model.Department;
import com.allenshibu.hrms.management.model.Employee;
import com.allenshibu.hrms.management.repository.DepartmentRepository;
import com.allenshibu.hrms.management.repository.EmployeeRepository;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(UUID id) {
        Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent()) {
            return department.get();
        } else {
            throw new DepartmentNotFoundException(id);
        }
    }

    public Department getDepartmentByDepartmentId(String departmentId) {
        Optional<Department> department = departmentRepository.findByDepartmentId(departmentId);

        if (department.isPresent()) {
            return department.get();
        } else {
            throw new DepartmentNotFoundException(departmentId);
        }
    }

    public Department addNewDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(UUID id, Department department) {
        Optional<Department> existingDepartment = departmentRepository.findById(id);

        if (existingDepartment.isPresent()) {
            Department currentDepartment = existingDepartment.get();

            currentDepartment.setDepartmentId(department.getDepartmentId());

            currentDepartment.setName(department.getName());

            return departmentRepository.save(currentDepartment);
        } else {
            throw new DepartmentNotFoundException(id);
        }
    }

    public void deleteDepartment(UUID id) {
        Optional<Department> department = departmentRepository.findById(id);

        if (department.isPresent()) {
            departmentRepository.delete(department.get());
        } else {
            throw new DepartmentNotFoundException(id);
        }
    }

    public void addEmployeeToDepartment(UUID departmentId, UUID employeeId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (department.isPresent() && employee.isPresent()) {
            Department currentDepartment = department.get();
            Employee currentEmployee = employee.get();

            // TODO: Throw appropriate exception if employee already exists in department
            if (currentDepartment.getEmployees().contains(currentEmployee)) {
                return;
            }

            currentDepartment.getEmployees().add(currentEmployee);

            departmentRepository.save(currentDepartment);
        } else {
            if (!department.isPresent()) {
                throw new DepartmentNotFoundException(departmentId);
            }
            if (!employee.isPresent()) {
                throw new EmployeeNotFoundException(employeeId);
            }
        }
    }

    public void removeEmployeeFromDepartment(UUID departmentId, UUID employeeId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        Optional<Employee> employee = employeeRepository.findById(employeeId);

        if (department.isPresent() && employee.isPresent()) {
            Department currentDepartment = department.get();
            Employee currentEmployee = employee.get();

            currentDepartment.getEmployees().remove(currentEmployee);

            departmentRepository.save(currentDepartment);
        } else {
            if (!department.isPresent()) {
                throw new DepartmentNotFoundException(departmentId);
            }
            if (!employee.isPresent()) {
                throw new EmployeeNotFoundException(employeeId);
            }
        }
    }
}
