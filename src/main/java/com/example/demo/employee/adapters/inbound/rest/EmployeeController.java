package com.example.demo.employee.adapters.inbound.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.employee.domain.Employee;
import com.example.demo.employee.ports.inbound.EmployeeUseCase;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeUseCase useCase;

    public EmployeeController(EmployeeUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<EmployeeDto> listAll() {
        return useCase.listAll().stream().map(EmployeeDto::fromDomain).collect(Collectors.toList());
    }

    @PostMapping
    public EmployeeDto create(@RequestBody EmployeeDto dto) {
        Employee created = useCase.create(dto.toDomain());
        return EmployeeDto.fromDomain(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getById(@PathVariable Long id) {
        return useCase.getById(id).map(e -> ResponseEntity.ok(EmployeeDto.fromDomain(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> update(@PathVariable Long id, @RequestBody EmployeeDto dto) {
        return useCase.update(id, dto.toDomain()).map(e -> ResponseEntity.ok(EmployeeDto.fromDomain(e)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = useCase.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
