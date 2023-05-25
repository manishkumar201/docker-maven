package com.ecom.dtos;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
private String id;
	
	private int quantity;
	
	@NotBlank(message = "message field cant be empty")
	private String colour;
	
	@NotBlank(message = "message field cant be empty")
	private String model;
	
	private int price;
}
