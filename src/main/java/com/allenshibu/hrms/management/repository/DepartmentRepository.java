package com.allenshibu.hrms.management.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.allenshibu.hrms.management.model.Department;

@Repository
public interface DepartmentRepository extends ListCrudRepository<Department, UUID> {

    Optional<Department> findByDepartmentId(String departmentId);
}
