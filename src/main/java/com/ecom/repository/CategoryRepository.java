package com.ecom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecom.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, String> {

	List<Category> findByCategoryName(String name);
}
