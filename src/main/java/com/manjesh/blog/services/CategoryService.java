package com.manjesh.blog.services;

import java.util.List;

import com.manjesh.blog.payloads.CategoryDto;

public interface CategoryService {

	public CategoryDto createCategory(CategoryDto categoryDto);
	
	public CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);
	
	public void deleteCategory(Integer categoryId);
	
	public CategoryDto getCategory(Integer CategoryId);
	
	public List<CategoryDto> getAllCategory();
	
}
