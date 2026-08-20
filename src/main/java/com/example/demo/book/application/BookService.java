package com.example.demo.book.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.book.domain.Book;
import com.example.demo.book.ports.inbound.BookUseCase;
import com.example.demo.book.ports.outbound.BookRepositoryPort;

@Service
public class BookService implements BookUseCase {
    private final BookRepositoryPort repository;

    public BookService(BookRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> listAll() {
        return repository.findAll();
    }

    @Override
    public Book create(Book book) {
        return repository.save(book);
    }

    @Override
    public Optional<Book> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Book> update(Long id, Book book) {
        return repository.findById(id).map(existing -> {
            existing.setTitle(book.getTitle());
            existing.setAuthor(book.getAuthor());
            return repository.save(existing);
        });
    }

    @Override
    public boolean delete(Long id) {
        Optional<Book> existing = repository.findById(id);
        if (existing.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
