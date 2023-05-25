package com.ecom.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table
@Entity
public class Category {

	@Id
	private String id;
	
	private String categoryName;
	
	@OneToMany(
			   mappedBy = "category",
			   cascade = CascadeType.REMOVE
			   )
	private List<Product> products = new ArrayList<>();
	
}
