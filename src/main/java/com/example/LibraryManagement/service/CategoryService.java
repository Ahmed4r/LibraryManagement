package com.example.LibraryManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.LibraryManagement.dto.CategoryRequest;
import com.example.LibraryManagement.dto.CategoryResponse;
import com.example.LibraryManagement.entity.Category;
import com.example.LibraryManagement.entity.Book;
import com.example.LibraryManagement.exception.CategoryNotFoundException;
import com.example.LibraryManagement.mapper.CategoryMapper;
import com.example.LibraryManagement.repository.CategoryRepo;

@Service
public class CategoryService {

    private final CategoryRepo categoryRepo;

    public CategoryService(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @Transactional
    public CategoryResponse addCategory(CategoryRequest request) {
        Category category = CategoryMapper.toEntity(request);
        return CategoryMapper.toResponse(categoryRepo.save(category));
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> getAllCategories() {
        return categoryRepo.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public CategoryResponse getCategoryById(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        return CategoryMapper.toResponse(category);
    }

    @Transactional
    public CategoryResponse updateCategory(Long id, CategoryRequest request) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        category.setName(request.getName());

        return CategoryMapper.toResponse(category);
    }

    @Transactional
    public void removeCategory(Long id) {
        Category category = categoryRepo.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));

        for (Book book : List.copyOf(category.getBooks())) {
            book.removeCategory(category);
        }

        categoryRepo.delete(category);
    }
}