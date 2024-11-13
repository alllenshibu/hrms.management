package com.allenshibu.hrms.employeemanagement.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.allenshibu.hrms.employeemanagement.model.Employee;

@Repository
public interface EmployeeRepository extends ListCrudRepository<Employee, UUID> {

    Optional<Employee> findByEmail(String email);
}
