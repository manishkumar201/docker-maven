package com.ecom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom.dtos.ApiResponse;
import com.ecom.dtos.CategoryDto;
import com.ecom.dtos.ProductDto;
import com.ecom.services.CategoryServices;
import com.ecom.services.ProductServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryServices categoryServices;

	@Autowired
	private ProductServices productServices;

	// get all
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAll() {
		List<CategoryDto> allCategoryDtos = categoryServices.getAll();
		return ResponseEntity.ok(allCategoryDtos);
	}

	// get one
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable String categoryId) {
		CategoryDto categoryDto = categoryServices.getOne(categoryId);
		return ResponseEntity.ok(categoryDto);
	}

	// create
	@PostMapping
	public ResponseEntity<CategoryDto> addEntity(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto addCategoryDto = categoryServices.addCategoryDto(categoryDto);
		return new ResponseEntity<CategoryDto>(addCategoryDto, HttpStatus.CREATED);
	}

	// create product in category
	@PostMapping("/{categoryId}/products")
	public ResponseEntity<ProductDto> createProuct(@Valid @RequestBody ProductDto productDto,
			@PathVariable String categoryId) {

		ProductDto productDto2 = productServices.addProduct(productDto, categoryId);
		return new ResponseEntity<ProductDto>(productDto2, HttpStatus.CREATED);
	}

	// update category of existing product
	@PutMapping("/{categoryId}/products/{productId}")
	public ResponseEntity<ProductDto> updateCategory(@PathVariable String categoryId, @PathVariable String productId) {
		ProductDto updateProductCategory = productServices.updateProductCategory(categoryId, productId);
		return ResponseEntity.ok(updateProductCategory);
	}

	// update
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updatEntity(@PathVariable String categoryId,
			@RequestBody CategoryDto categoryDto) {
		CategoryDto categoryDto2 = categoryServices.updateCategoryDto(categoryDto, categoryId);

		return ResponseEntity.ok(categoryDto2);
	}

	// delete
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deletEntity(@PathVariable String categoryId) {
		categoryServices.delete(categoryId);
		return new ResponseEntity<ApiResponse>(
				ApiResponse.builder().message("category with id deleted").success(false).build(), HttpStatus.ACCEPTED);

	}

	// search
	@GetMapping("/search/{keywords}")
	public ResponseEntity<List<CategoryDto>> searchEntity(@PathVariable String keywords) {

		return ResponseEntity.ok(categoryServices.searchCategoryDto(keywords));
	}
}
