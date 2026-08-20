package com.example.demo.employee.ports.outbound;

import com.example.demo.employee.domain.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepositoryPort {
    Employee save(Employee employee);
    List<Employee> findAll();
    Optional<Employee> findById(Long id);
    void deleteById(Long id);
}
