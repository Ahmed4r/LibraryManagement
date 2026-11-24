package com.example.LibraryManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.LibraryManagement.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {

}