package com.springboot.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}
	//build add categories api
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
		CategoryDto saveCategory = categoryService.addCategory(categoryDto);
		return new ResponseEntity<>(saveCategory,HttpStatus.CREATED);
	}
	//build the findbyid api
	@GetMapping("{id}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("id") Long categoryId){
		CategoryDto categoryDto = categoryService.getCategory(categoryId);
		return  ResponseEntity.ok(categoryDto);
	}
	//get all categories api
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getCategories(){
		return ResponseEntity.ok(categoryService.getAllCategories());
	}
	//build update categories api
    @PreAuthorize("hasRole('ADMIN')")
	@PutMapping("{id}")
	public ResponseEntity<CategoryDto> updateCategory(@PathVariable("id") Long categoryId,@RequestBody CategoryDto categoryDto){
		return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
	}
    //delete categories api
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long categoryId){
    	categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok("category is deleted successfully....");
    }
}
