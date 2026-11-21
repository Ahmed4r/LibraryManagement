package com.example.LibraryManagement.dto;

import java.util.List;

public class AuthorResponse {
    private List<BookResponse> authorBooks;
    private String name;
    private String nationality;
    private Long id;

    public Long getId() {
        return id;
    }

    public AuthorResponse(List<BookResponse> authorBooks, String name, String nationality, Long id) {
        this.authorBooks = authorBooks;
        this.name = name;
        this.nationality = nationality;
        this.id = id;
    }

    public List<BookResponse> getAuthorBooks() {
        return authorBooks;
    }

    public String getName() {
        return name;
    }

    public String getNationality() {
        return nationality;
    }
}
