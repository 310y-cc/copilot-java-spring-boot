package com.example.demo.employee.adapters.outbound.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataEmployeeRepository extends JpaRepository<EmployeeJpaEntity, Long> {
}
