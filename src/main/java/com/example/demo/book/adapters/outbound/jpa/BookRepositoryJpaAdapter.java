package com.example.demo.book.adapters.outbound.jpa;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.example.demo.book.domain.Book;
import com.example.demo.book.ports.outbound.BookRepositoryPort;

@Repository
public class BookRepositoryJpaAdapter implements BookRepositoryPort {
    private final SpringDataBookRepository repo;

    public BookRepositoryJpaAdapter(SpringDataBookRepository repo) {
        this.repo = repo;
    }

    @Override
    public Book save(Book book) {
        BookJpaEntity entity = toEntity(book);
        BookJpaEntity saved = repo.save(entity);
        return toDomain(saved);
    }

    @Override
    public List<Book> findAll() {
        return repo.findAll().stream().map(this::toDomain).collect(Collectors.toList());
    }

    @Override
    public Optional<Book> findById(Long id) {
        return repo.findById(id).map(this::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    private Book toDomain(BookJpaEntity e) {
        if (e == null) return null;
        return new Book(e.getId(), e.getTitle(), e.getAuthor());
    }

    private BookJpaEntity toEntity(Book d) {
        if (d == null) return null;
        return new BookJpaEntity(d.getId(), d.getTitle(), d.getAuthor());
    }
}
