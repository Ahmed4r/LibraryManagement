package com.example.LibraryManagement.mapper;

import com.example.LibraryManagement.dto.CategoryRequest;
import com.example.LibraryManagement.dto.CategoryResponse;
import com.example.LibraryManagement.entity.Category;

public class CategoryMapper {

    public static Category toEntity(CategoryRequest request) {
        return new Category(request.getName());
    }

    public static CategoryResponse toResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName());
    }
}