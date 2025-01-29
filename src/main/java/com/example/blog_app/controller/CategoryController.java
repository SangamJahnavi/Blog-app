package com.example.blog_app.controller;

import com.example.blog_app.entities.Category;
import com.example.blog_app.payloads.ApiResponse;
import com.example.blog_app.payloads.CategoryDto;
import com.example.blog_app.services.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;
//    POST
    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }

//    UPDATE
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto categoryDto1=this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }

//    DELETE
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deletecategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted successfully",true),HttpStatus.OK);
    }

//    GET CATEGORY BY ID
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getUserbyId(@PathVariable Integer categoryId){
        CategoryDto categoryDto=this.categoryService.getCategorybyId(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

//    GET ALL CATEGORIES
    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllUsers(){
        List<CategoryDto> categoryDtos=this.categoryService.getAllCategory();
        return ResponseEntity.ok(categoryDtos);
    }
}
