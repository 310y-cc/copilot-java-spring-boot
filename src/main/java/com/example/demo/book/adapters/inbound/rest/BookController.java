package com.example.demo.book.adapters.inbound.rest;

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

import com.example.demo.book.domain.Book;
import com.example.demo.book.ports.inbound.BookUseCase;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookUseCase useCase;

    public BookController(BookUseCase useCase) {
        this.useCase = useCase;
    }

    @GetMapping
    public List<BookDto> listAll() {
        return useCase.listAll().stream().map(BookDto::fromDomain).collect(Collectors.toList());
    }

    @PostMapping
    public BookDto create(@RequestBody BookDto dto) {
        Book created = useCase.create(dto.toDomain());
        return BookDto.fromDomain(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getById(@PathVariable Long id) {
        return useCase.getById(id).map(b -> ResponseEntity.ok(BookDto.fromDomain(b)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookDto> update(@PathVariable Long id, @RequestBody BookDto dto) {
        return useCase.update(id, dto.toDomain()).map(b -> ResponseEntity.ok(BookDto.fromDomain(b)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = useCase.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
