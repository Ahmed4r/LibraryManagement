package com.example.LibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraryManagement.entity.Author;

public interface AuthorRepo extends JpaRepository<Author,Long> {
    
}
