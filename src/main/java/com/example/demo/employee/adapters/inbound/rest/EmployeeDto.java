package com.example.demo.employee.adapters.inbound.rest;

import com.example.demo.employee.domain.Employee;

public class EmployeeDto {
    private Long id;
    private String name;
    private String email;

    public EmployeeDto() {}

    public EmployeeDto(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Employee toDomain() {
        return new Employee(id, name, email);
    }

    public static EmployeeDto fromDomain(Employee e) {
        if (e == null) return null;
        return new EmployeeDto(e.getId(), e.getName(), e.getEmail());
    }
}
