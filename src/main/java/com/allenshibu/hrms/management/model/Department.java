package com.allenshibu.hrms.management.model;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Department ID is required")
    @Column(nullable = false, unique = true)
    private String departmentId;

    @NotNull(message = "Department name is required")
    @Column(nullable = false)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "employee_department",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id")
    )
    private List<Employee> employees;
}
