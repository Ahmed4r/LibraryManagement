package com.example.LibraryManagement.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.example.LibraryManagement.dto.BookRequest;
import com.example.LibraryManagement.dto.BookResponse;
import com.example.LibraryManagement.dto.BookSummary;
import com.example.LibraryManagement.service.BookService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping("/api/v1/books")
@Validated
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

    @GetMapping("/search")
    public ResponseEntity<Page<BookResponse>> searchBooksPagination(
            @RequestParam String title,
            @PositiveOrZero @RequestParam(defaultValue = "0") int pageNumber,
            @Positive @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity
                .ok(bookService.searchBooks(title, pageNumber, pageSize, sortBy, direction));
    }

    @GetMapping("/filter")
    public ResponseEntity<Page<BookResponse>> filterBooks(@RequestParam(required = false) String title,
            @RequestParam(required = false) Integer year,
            @PositiveOrZero @RequestParam(defaultValue = "0") int pageNumber,
            @Positive @RequestParam(defaultValue = "10") int pageSize)

    {
        return ResponseEntity.ok(bookService.filterBooks(title, year, pageNumber, pageSize));
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

    @GetMapping("/find")
    public ResponseEntity<List<BookResponse>> findBookWithTitle(@RequestParam String title) {
        return ResponseEntity.ok(bookService.searchByTitle(title));
    }

    @GetMapping("/summaries")
    public ResponseEntity<List<BookSummary>> getBookSummaries() {
        return ResponseEntity.ok(bookService.getBookSummaries());
    }

    @GetMapping("/optimized")
    public ResponseEntity<List<BookResponse>> getOptimizedBooks() {
        return ResponseEntity.ok(bookService.getAllBooksOptimized());
    }

}