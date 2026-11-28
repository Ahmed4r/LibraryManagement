package com.example.LibraryManagement.specification;

import org.springframework.data.jpa.domain.Specification;

import com.example.LibraryManagement.entity.Book;

public class BookSpecification {
    public Specification<Book> hasTitle(String title) {

        return (root, query, cb) -> cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");

    }

    public Specification<Book> hasPublicationYear(Integer year) {
        return (root, query, cb) -> cb.equal(root.get("publicationYear"), year);
    }

    public Specification<Book> hasTitleAndYear(String title, Integer year) {
        Specification<Book> spec = null;
        if (spec == null) {
            if (title != null && !title.isBlank()) {
                spec = hasTitle(title);
            }
        }

        if (year != null) {
            if (spec == null) {
                spec = hasPublicationYear(year);
            } else {
                spec = spec.and(hasPublicationYear(year));
            }
        }

        return spec;
    }
}
