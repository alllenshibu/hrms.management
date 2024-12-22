package com.allenshibu.hrms.management.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.allenshibu.hrms.management.model.Employee;

@Repository
public interface EmployeeRepository extends ListCrudRepository<Employee, UUID> {

    Optional<Employee> findByEmail(String email);

    Optional<Employee> findByEmployeeId(String employeeId);
}