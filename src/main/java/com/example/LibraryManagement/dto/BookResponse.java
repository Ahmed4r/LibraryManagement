package com.example.LibraryManagement.dto;

public class BookResponse {
    private Integer publicationYear;
    private String title;
    private Long authorId;
    private String authorName;

    private Long id;
    private String isbn;

    public BookResponse(Integer publicationYear, String title, Long authorId, String authorName, Long id, String isbn) {
        this.publicationYear = publicationYear;
        this.title = title;
        this.authorId = authorId;
        this.authorName = authorName;
        this.id = id;
        this.isbn = isbn;
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
}
