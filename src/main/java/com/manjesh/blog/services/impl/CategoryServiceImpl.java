package com.manjesh.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.manjesh.blog.entities.Category;
import com.manjesh.blog.exceptions.ResourceNotFoundException;
import com.manjesh.blog.payloads.CategoryDto;
import com.manjesh.blog.repositories.CategoryRepo;
import com.manjesh.blog.services.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService  {
    @Autowired
	private  CategoryRepo categoryRepo;
    
    @Autowired
    private ModelMapper modelMapper;
    
	
	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category cat=this.modelMapper.map(categoryDto, Category.class);
		Category addCategory=this.categoryRepo.saveAndFlush(cat);
		return this.modelMapper.map(addCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category Id", categoryId));
		cat.setCategoryDescription(categoryDto.getCategoryDescription());
		cat.setCategoryTitle(categoryDto.getCategoryTitle());
		
		Category updateCategory=this.categoryRepo.save(cat);
		return this.modelMapper.map(updateCategory, CategoryDto.class);
	}

	@Override
	public void deleteCategory(Integer categoryId) {
		Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category", "CategoryId", categoryId));
		this.categoryRepo.delete(cat);
		
	}

	@Override
	public CategoryDto getCategory(Integer CategoryId) {
		Category cat=this.categoryRepo.findById(CategoryId).orElseThrow(()->new ResourceNotFoundException("Category","categoryID", CategoryId));
         
		return this.modelMapper.map(cat, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category>categories=this.categoryRepo.findAll();
		List<CategoryDto> catDtos=categories.stream().map((cat)->this.modelMapper.map(cat, CategoryDto.class)).collect(Collectors.toList());
		return catDtos;
	}

	
}
