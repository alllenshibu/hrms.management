package com.allenshibu.hrms.employeemanagement.repository;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ListCrudRepository<T, ID> extends CrudRepository<T, ID> {

}
