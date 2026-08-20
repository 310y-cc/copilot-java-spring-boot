package com.example.demo.employee.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.employee.domain.Employee;
import com.example.demo.employee.ports.inbound.EmployeeUseCase;
import com.example.demo.employee.ports.outbound.EmployeeRepositoryPort;

@Service
public class EmployeeService implements EmployeeUseCase {
    private final EmployeeRepositoryPort repository;

    public EmployeeService(EmployeeRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> listAll() {
        return repository.findAll();
    }

    @Override
    public Employee create(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Optional<Employee> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Employee> update(Long id, Employee employee) {
        return repository.findById(id).map(existing -> {
            existing.setFullName(employee.getFullName());
            existing.setEmail(employee.getEmail());
            return repository.save(existing);
        });
    }

    @Override
    public boolean delete(Long id) {
        Optional<Employee> existing = repository.findById(id);
        if (existing.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
