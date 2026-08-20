package com.example.demo.employee.ports.inbound;

import com.example.demo.employee.domain.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeUseCase {
    List<Employee> listAll();
    Employee create(Employee employee);
    Optional<Employee> getById(Long id);
    Optional<Employee> update(Long id, Employee employee);
    boolean delete(Long id);
}
