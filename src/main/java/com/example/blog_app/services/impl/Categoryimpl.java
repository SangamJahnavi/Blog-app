package com.example.blog_app.services.impl;

import com.example.blog_app.entities.Category;
import com.example.blog_app.exceptions.ResourceNotFoundException;
import com.example.blog_app.payloads.CategoryDto;
import com.example.blog_app.payloads.Userdto;
import com.example.blog_app.repositories.CategoryRepo;
import com.example.blog_app.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Categoryimpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categorydto) {
        Category category=this.modelMapper.map(categorydto,Category.class);
        Category categorysaved=this.categoryRepo.save(category);
        return this.modelMapper.map(categorysaved,CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","id",categoryId));
        category.setCategory_id(categoryDto.getCategory_id());
        category.setCategory_name(categoryDto.getCategory_name());
        category.setCategory_description(categoryDto.getCategory_description());
        this.categoryRepo.save(category);
        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));
        this.categoryRepo.delete(category);

    }

    @Override
    public CategoryDto getCategorybyId(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","id",categoryId));

        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        List<Category> categories= this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos=categories.stream()
                .map(category -> this.modelMapper.map(category, CategoryDto.class)) // Corrected
                .toList();

        return categoryDtos;
    }
}
