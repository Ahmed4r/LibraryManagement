package com.example.LibraryManagement.mapper;

import java.util.List;

import com.example.LibraryManagement.dto.AuthorRequest;
import com.example.LibraryManagement.dto.AuthorResponse;
import com.example.LibraryManagement.dto.BookResponse;
import com.example.LibraryManagement.entity.Author;

public class AuthorMapper {
    public static Author toEntity(AuthorRequest request) {
        return new Author(request.getName(), request.getNationality());
    }

    public static AuthorResponse toResponse(Author author) {
        List<BookResponse> books = author.getAuthorBooks()
                .stream()
                .map(book -> BookMapper.toResponse(book))
                .toList();
        return new AuthorResponse(books, author.getName(), author.getNationality(), author.getId());
    }
}
