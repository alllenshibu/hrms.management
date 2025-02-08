package com.allenshibu.hrms.management.model;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "Employee ID is required")
    @Column(nullable = false)
    private String employeeId;

    @NotNull(message = "First name is required")
    @Column(nullable = false)
    private String firstName;

    private String middleName;

    @NotNull(message = "Last name is required")
    @Column(nullable = false)
    private String lastName;

    @NotNull(message = "Email is required")
    @Column(nullable = false)
    @Email(message = "Invalid email")
    private String email;

    @Email
    private String alternateEmail;

    @ManyToMany(mappedBy = "employees", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("employees")
    private List<Department> departments;
}