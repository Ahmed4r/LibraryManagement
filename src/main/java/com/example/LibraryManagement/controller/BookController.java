package com.example.LibraryManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.LibraryManagement.dto.BookRequest;
import com.example.LibraryManagement.dto.BookResponse;
import com.example.LibraryManagement.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponse> addBook(
            @Valid @RequestBody BookRequest request) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookService.addBook(request));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {

        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBookById(
            @PathVariable Long id) {

        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponse> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookRequest request) {

        return ResponseEntity.ok(
                bookService.updateBook(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeBook(
            @PathVariable Long id) {

        bookService.removeBook(id);

        return ResponseEntity.noContent().build();
    }
}