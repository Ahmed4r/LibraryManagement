package com.example.LibraryManagement.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LibraryManagement.dto.BookRequest;
import com.example.LibraryManagement.dto.BookResponse;
import com.example.LibraryManagement.entity.Author;
import com.example.LibraryManagement.entity.Book;
import com.example.LibraryManagement.entity.Category;
import com.example.LibraryManagement.exception.AuthorNotFoundException;
import com.example.LibraryManagement.exception.BookNotFoundException;
import com.example.LibraryManagement.exception.CategoryNotFoundException;
import com.example.LibraryManagement.mapper.BookMapper;
import com.example.LibraryManagement.repository.AuthorRepo;
import com.example.LibraryManagement.repository.BookRepo;
import com.example.LibraryManagement.repository.CategoryRepo;

@Service
public class BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;
    private final CategoryRepo categoryRepo;

    public BookService(
            BookRepo bookRepo,
            AuthorRepo authorRepo,
            CategoryRepo categoryRepo) {

        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.categoryRepo = categoryRepo;
    }

    @Transactional
    public BookResponse addBook(BookRequest request) {

        Author author = authorRepo.findById(request.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(request.getAuthorId()));

        Book book = BookMapper.toEntity(request, author);

        assignCategories(book, request.getCategoryIds());

        Book savedBook = bookRepo.save(book);

        return BookMapper.toResponse(savedBook);
    }

    @Transactional(readOnly = true)
    public List<BookResponse> getAllBooks() {
        return bookRepo.findAll()
                .stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public BookResponse getBookById(Long bookId) {

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        return BookMapper.toResponse(book);
    }

    @Transactional
    public void removeBook(Long bookId) {

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        for (Category category : List.copyOf(book.getCategories())) {
            book.removeCategory(category);
        }

        bookRepo.delete(book);
    }

    @Transactional
    public BookResponse updateBook(Long bookId, BookRequest request) {

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));

        Author author = authorRepo.findById(request.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(request.getAuthorId()));

        book.setTitle(request.getTitle());
        book.setIsbn(request.getIsbn());
        book.setPublicationYear(request.getPublicationYear());
        book.setAuthor(author);

        for (Category category : List.copyOf(book.getCategories())) {
            book.removeCategory(category);
        }

        assignCategories(book, request.getCategoryIds());

        return BookMapper.toResponse(book);
    }

    private void assignCategories(Book book, Set<Long> categoryIds) {

        if (categoryIds == null) {
            return;
        }

        for (Long categoryId : categoryIds) {

            Category category = categoryRepo.findById(categoryId)
                    .orElseThrow(() -> new CategoryNotFoundException(categoryId));

            book.addCategory(category);
        }
    }
}