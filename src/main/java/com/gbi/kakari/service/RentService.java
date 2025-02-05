package com.gbi.kakari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbi.kakari.dto.RentDTO;
import com.gbi.kakari.entity.car.Product;
import com.gbi.kakari.entity.rent.Rent;
import com.gbi.kakari.entity.rent.RentLocation;
import com.gbi.kakari.repository.car.ProductRepository;
import com.gbi.kakari.repository.rent.RentLocationRepository;
import com.gbi.kakari.repository.rent.RentRepository;

@Service
public class RentService {
	private final ProductRepository productRepository;
	private final RentRepository rentRepository;
	private final RentLocationRepository rentLocationRepository;

	@Autowired
	public RentService(ProductRepository productRepository, RentRepository rentRepository, RentLocationRepository rentLocationRepository) {
		this.productRepository = productRepository;
		this.rentRepository = rentRepository;
		this.rentLocationRepository = rentLocationRepository;
	}

	public Integer rentCar(String userId, Product product, RentDTO rentDTO) {
		Long durationHours = rentDTO.getDurationHours();
		Long totalRentPrice = product.getPrice() * durationHours;

		Rent rent = Rent.builder()
			.userId(userId)
			.productId(rentDTO.getProductId())
			.rentLocationId(rentDTO.getRentLocationId())
			.rentStartDate(rentDTO.getRentStartDateTime())
			.rentEndDate(rentDTO.getRentEndDateTime())
			.rentPrice(totalRentPrice)
			.build();
		return rentRepository.save(rent).getRentId();
	}

	public List<RentLocation> getRentLocations() {
		return rentLocationRepository.findAll();
	}

	public List<Product> getRentCarProducts() {
		return productRepository.findAll();
	}

	public Long getPrice(Product product, RentDTO rentDTO) {
		return product.getPrice() * rentDTO.getDurationHours();
	}

	public RentLocation getRentLocation(Integer rentLocationId) {
		return rentLocationRepository.findById(rentLocationId).orElse(null);
	}
}
