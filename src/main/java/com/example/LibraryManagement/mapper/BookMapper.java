package com.example.LibraryManagement.mapper;

import com.example.LibraryManagement.dto.BookRequest;
import com.example.LibraryManagement.dto.BookResponse;
import com.example.LibraryManagement.entity.Author;
import com.example.LibraryManagement.entity.Book;

public class BookMapper {
    public static BookResponse toResponse(Book book) {
        return new BookResponse(book.getPublicationYear(), book.getTitle(), book.getAuthor().getId(),
                book.getAuthor().getName(), book.getId(), book.getIsbn());
    }

    public static Book toEntity(BookRequest request, Author author) {
        return new Book(
                request.getIsbn(),
                request.getPublicationYear(),
                request.getTitle(),
                author);
    }
}
