package com.example.demo.book.adapters.outbound.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataBookRepository extends JpaRepository<BookJpaEntity, Long> {
}
