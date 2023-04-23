package com.carrefour.dtos;

import lombok.Data;

@Data
public class ProductDto {
	private String nom;
	private float price;
	private String barcode;
	private String state;

}
