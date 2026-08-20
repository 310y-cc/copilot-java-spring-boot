package com.example.demo.book.ports.inbound;

import com.example.demo.book.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookUseCase {
    List<Book> listAll();
    Book create(Book book);
    Optional<Book> getById(Long id);
    Optional<Book> update(Long id, Book book);
    boolean delete(Long id);
}
