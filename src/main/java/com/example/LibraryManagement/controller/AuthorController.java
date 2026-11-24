package com.example.LibraryManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryManagement.dto.AuthorRequest;
import com.example.LibraryManagement.dto.AuthorResponse;
import com.example.LibraryManagement.service.AuthorService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    // creatre author
    @PostMapping
    public ResponseEntity<AuthorResponse> addAuthor(
            @Valid @RequestBody AuthorRequest request) {
        AuthorResponse author = authorService.addAuthor(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @GetMapping()
    public ResponseEntity<List<AuthorResponse>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponse> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponse> updateAuthor(@PathVariable Long id,
            @Valid @RequestBody AuthorRequest request) {

        return ResponseEntity.ok(authorService.updateAuthor(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeAuthor(
            @PathVariable Long id) {

        authorService.removeAuthor(id);

        return ResponseEntity.noContent().build();
    }

}