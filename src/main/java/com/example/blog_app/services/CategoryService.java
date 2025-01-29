package com.example.blog_app.services;

import com.example.blog_app.entities.Category;
import com.example.blog_app.payloads.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CategoryService {

    CategoryDto createCategory(CategoryDto categorydto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
    void deleteCategory(Integer categoryId);
    CategoryDto getCategorybyId(Integer categoryId);
    List<CategoryDto> getAllCategory();

}
