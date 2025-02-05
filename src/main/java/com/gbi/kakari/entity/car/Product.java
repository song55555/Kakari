package com.gbi.kakari.entity.car;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product implements Serializable { // Implement Serializable interface
	@Serial // Explicitly declare serialVersionUID
	private static final long serialVersionUID = 1L; // Recommended for Serializable classes

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id", nullable = false)
	private Integer productId;

	@Column(name = "product_name", nullable = false, length = 20)
	private String productName;

	@Column(name = "price", precision = 10, scale = 2)
	private Long price;

	@Column(name = "product_image", length = 255, nullable = false)
	private String productImage;

	@Builder
	public Product(String productName, Long price, String productImage) {
		this.productName = productName;
		this.price = price;
		this.productImage = productImage;
	}

	public boolean IsNullProduct() {
		return productName == null || price == null || productImage == null;
	}
}
