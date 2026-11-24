package com.example.LibraryManagement.mapper;

import java.util.Set;
import java.util.stream.Collectors;

import com.example.LibraryManagement.dto.BookRequest;
import com.example.LibraryManagement.dto.BookResponse;
import com.example.LibraryManagement.dto.CategoryResponse;
import com.example.LibraryManagement.entity.Author;
import com.example.LibraryManagement.entity.Book;

public class BookMapper {

    public static BookResponse toResponse(Book book) {

        Set<CategoryResponse> categories = book.getCategories()
                .stream()
                .map(CategoryMapper::toResponse)
                .collect(Collectors.toSet());

        return new BookResponse(
                book.getPublicationYear(),
                book.getTitle(),
                book.getAuthor().getId(),
                book.getAuthor().getName(),
                book.getId(),
                book.getIsbn(),
                categories);
    }

    public static Book toEntity(BookRequest request, Author author) {
        return new Book(
                request.getIsbn(),
                request.getPublicationYear(),
                request.getTitle(),
                author);
    }
}