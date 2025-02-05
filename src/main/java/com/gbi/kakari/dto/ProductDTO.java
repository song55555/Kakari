package com.gbi.kakari.dto;

import com.gbi.kakari.entity.car.Product;

import lombok.Data;

@Data
public class ProductDTO {
	private String productName;
	private Long price;
	private String productImage;

	public static ProductDTO fromEntity(Product product) {
		ProductDTO dto = new ProductDTO();
		dto.setProductName(product.getProductName());
		dto.setPrice(product.getPrice());
		dto.setProductImage(product.getProductImage());
		return dto;
	}
}
