package com.example.LibraryManagement.dto;

import java.util.Set;

public class BookResponse {

    private Integer publicationYear;
    private String title;
    private Long authorId;
    private String authorName;
    private Long id;
    private String isbn;
    private Set<CategoryResponse> categories;

    public BookResponse(
            Integer publicationYear,
            String title,
            Long authorId,
            String authorName,
            Long id,
            String isbn,
            Set<CategoryResponse> categories) {

        this.publicationYear = publicationYear;
        this.title = title;
        this.authorId = authorId;
        this.authorName = authorName;
        this.id = id;
        this.isbn = isbn;
        this.categories = categories;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public String getTitle() {
        return title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public Long getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public Set<CategoryResponse> getCategories() {
        return categories;
    }
}