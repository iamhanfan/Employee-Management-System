package com.ems.app.sys.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    private long id;

    @NotBlank(message = "Employee name is mandatory")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 30 characters") // <-- UPDATED
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be exactly 10 digits") // <-- UPDATED
    @Column(name = "phone")
    private String phone;

    @NotBlank(message = "Gender is mandatory")
    @Column(name = "gender")
    private String gender;

    @NotNull(message = "Salary cannot be null")
    @Min(value = 0, message = "Salary must be a positive number")
    @Column(name = "salary")
    private Double salary;
    
    @NotBlank(message = "Role is mandatory")
    @Column(name = "role")
    private String role;

    // Default constructor (required by JPA)
    public Employee() {
    }

    // --- Getters and Setters ---

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}