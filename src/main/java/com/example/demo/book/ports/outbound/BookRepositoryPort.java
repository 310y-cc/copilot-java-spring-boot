package com.example.demo.book.ports.outbound;

import com.example.demo.book.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepositoryPort {
    Book save(Book book);
    List<Book> findAll();
    Optional<Book> findById(Long id);
    void deleteById(Long id);
}
