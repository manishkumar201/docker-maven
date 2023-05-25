package com.ecom.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
public class Product {
	@Id
	private String id;
	
	private int quantity;
	
	private String colour;
	
	private String model;
	
	private int price;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Category category;
}
