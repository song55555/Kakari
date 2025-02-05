package com.gbi.kakari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbi.kakari.entity.car.Product;
import com.gbi.kakari.entity.car.ProductColor;
import com.gbi.kakari.entity.car.option.OptionType;
import com.gbi.kakari.entity.car.option.ProductOption;
import com.gbi.kakari.repository.car.ProductColorRepository;
import com.gbi.kakari.repository.car.ProductRepository;
import com.gbi.kakari.repository.car.option.OptionTypeRepository;
import com.gbi.kakari.repository.car.option.ProductOptionRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	private final ProductColorRepository productColorRepository;
	private final OptionTypeRepository optionTypeRepository;
	private final ProductOptionRepository productOptionRepository;

	@Autowired
	public ProductService(
		ProductRepository productRepository, ProductColorRepository productColorRepository,
		OptionTypeRepository optionTypeRepository, ProductOptionRepository productOptionRepository) {
		this.productRepository = productRepository;
		this.productColorRepository = productColorRepository;
		this.optionTypeRepository = optionTypeRepository;
		this.productOptionRepository = productOptionRepository;
	}

	// Product

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public Product getProductById(Integer productId) {
		return productRepository.findById(productId).orElse(null);
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product updateProduct(Integer productId, Product product) {
		Product existingProduct = productRepository.findById(productId).orElse(null);
		if (existingProduct != null) {
			existingProduct.setProductName(product.getProductName());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setProductImage(product.getProductImage());
			return productRepository.save(existingProduct);
		}
		return null;
	}

	public void removeProduct(Integer productId) {
		productRepository.deleteById(productId);
	}

	// Color

	public String getProductColorName(Integer productId) {
		ProductColor productColor = productColorRepository.findById(productId).orElse(null);
		if (productColor != null) {
			return productColor.getColorName();
		}
		return null;
	}

	public List<ProductColor> getColorsByProductId(Integer productId) {
		return productColorRepository.findByProductId(productId);
	}

	// Option

	public List<OptionType> getAllOptionTypes() {
		return optionTypeRepository.findAll();
	}

	public String getOptionTypeName(Integer optionTypeId) {
		OptionType optionType = optionTypeRepository.findById(optionTypeId).orElse(null);
		if (optionType != null) {
			return optionType.getOptionName();
		}
		return null;
	}

	public List<ProductOption> getOptionsByProductId(Integer productId) {
		return productOptionRepository.findByProductId(productId);
	}
}
