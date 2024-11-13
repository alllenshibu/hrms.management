package com.allenshibu.hrms.management.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
    private String[] alternateEmails;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}
