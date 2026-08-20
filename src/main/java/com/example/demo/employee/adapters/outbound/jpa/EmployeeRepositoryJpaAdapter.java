package com.example.demo.employee.adapters.outbound.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.employee.domain.Employee;
import com.example.demo.employee.ports.outbound.EmployeeRepositoryPort;

@Repository
public class EmployeeRepositoryJpaAdapter implements EmployeeRepositoryPort {
    private final SpringDataEmployeeRepository repo;

    public EmployeeRepositoryJpaAdapter(SpringDataEmployeeRepository repo) {
        this.repo = repo;
    }

    @Override
    public Employee save(Employee employee) {
        EmployeeJpaEntity entity = toEntity(employee);
        EmployeeJpaEntity saved = repo.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Employee> findAll() {
        return repo.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return repo.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    private Employee toDomain(EmployeeJpaEntity e) {
        if (e == null) return null;
        return new Employee(e.getId(), e.getFullName(), e.getEmail());
    }

    private EmployeeJpaEntity toEntity(Employee d) {
        if (d == null) return null;
        return new EmployeeJpaEntity(d.getId(), d.getFullName(), d.getEmail());
    }
}
