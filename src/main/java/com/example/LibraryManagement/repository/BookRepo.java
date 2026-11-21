package com.example.LibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraryManagement.entity.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
