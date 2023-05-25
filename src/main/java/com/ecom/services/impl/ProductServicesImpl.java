package com.ecom.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.dtos.ProductDto;
import com.ecom.entity.Category;
import com.ecom.entity.Product;
import com.ecom.repository.CategoryRepository;
import com.ecom.repository.ProductRepository;
import com.ecom.services.ProductServices;

@Service
public class ProductServicesImpl implements ProductServices{

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper mapper;
	
	//--------------APIs--------------
	@Override
	public List<ProductDto> getAllProduct() {
		List<Product> findAllproducts = productRepository.findAll();
		List<ProductDto> productDtosList = findAllproducts.stream()
				.map(product -> mapper.map(product, ProductDto.class)).collect(Collectors.toList());
		return productDtosList;
	}
	
	@Override
	public ProductDto getProduct(String productDtoId) {
		Product product = productRepository.findById(productDtoId).orElse(null);
		return mapper.map(product, ProductDto.class);
	}
	
	@Override
	public ProductDto addProduct(ProductDto productDto) {
		Product product = mapper.map(productDto, Product.class);
		product.setId(UUID.randomUUID().toString());
		Product product2 = productRepository.save(product);
		return mapper.map(product2, ProductDto.class);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto, String productDtoId) {

		Product product = productRepository.findById(productDtoId).orElse(null);
		product.setColour(productDto.getColour());
		product.setModel(productDto.getModel());
		product.setPrice(productDto.getPrice());
		product.setQuantity(productDto.getQuantity());

		Product savedProduct = productRepository.save(product);
		return mapper.map(savedProduct, ProductDto.class);
	}

	@Override
	public void deleteProduct(String productDtoId) {
		productRepository.deleteById(productDtoId);
	}
	
	
	
	
	
	@Override
	public List<ProductDto> searchProduct(int keyword) {
		List<Product> productInRange = productRepository.findProductWithPriceInRange(keyword);
		List<ProductDto> list = productInRange.stream().map(product -> mapper.map(product, ProductDto.class))
				.collect(Collectors.toList());
		return list;
	}
	
	@Override
	public ProductDto addProduct(ProductDto productDto, String categoryId) {
		
		// get the category of given id
		Category category = categoryRepository.findById(categoryId).orElse(null);
		Product product = mapper.map(productDto, Product.class);
		product.setCategory(category);
		product.setId(UUID.randomUUID().toString());
		Product savedProduct = productRepository.save(product);
		return mapper.map(savedProduct, ProductDto.class);
	}
	
	@Override
	public ProductDto updateProductCategory(String categoryId, String productDtoId) {
		Product product = productRepository.findById(productDtoId).orElse(null);
		Category category = categoryRepository.findById(categoryId).orElse(null);
		product.setCategory(category);
		Product product2 = productRepository.save(product);
		return mapper.map(product2, ProductDto.class);
	}
	
}
