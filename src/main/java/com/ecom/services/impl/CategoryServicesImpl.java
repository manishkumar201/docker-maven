package com.ecom.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.ecom.dtos.CategoryDto;
import com.ecom.entity.Category;
import com.ecom.repository.CategoryRepository;
import com.ecom.services.CategoryServices;


@Service
public class CategoryServicesImpl implements CategoryServices {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper mapper;

	@Override
	public CategoryDto addCategoryDto(CategoryDto categoryDto) {
		Category category = mapper.map(categoryDto, Category.class);
		category.setId(UUID.randomUUID().toString());
		Category savedCategory = categoryRepository.save(category);
		return mapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto updateCategoryDto(CategoryDto categoryDto, String categoryStringId) {

		Category category = categoryRepository.findById(categoryStringId).orElse(null);
		category.setCategoryName(categoryDto.getCategoryName());
		Category savedCategory = categoryRepository.save(category);
		return mapper.map(savedCategory, CategoryDto.class);
	}

	@Override
	public CategoryDto getOne(String categoryStringId) {
		Category category = categoryRepository.findById(categoryStringId).orElse(null);
		return mapper.map(category, CategoryDto.class);
	}

	@Override
	public List<CategoryDto> getAll() {
		List<Category> findAllCategories = categoryRepository.findAll();
		List<CategoryDto> collectdCategoryDtos = findAllCategories.stream()
				.map(category -> mapper.map(category, CategoryDto.class)).collect(Collectors.toList());
		return collectdCategoryDtos;
	}

	@Override
	public List<CategoryDto> searchCategoryDto(String keywords) {
		List<Category> categoryList = categoryRepository.findByCategoryName(keywords);
		List<CategoryDto> list = categoryList.stream().map(category -> mapper.map(category, CategoryDto.class))
				.collect(Collectors.toList());

		return list;
	}

	@Override
	public void delete(String categoryStringId) {
		categoryRepository.deleteById(categoryStringId);

	}

}
