package com.gbi.kakari.repository.car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.car.ProductColor;

@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
	List<ProductColor> findByProductId(Integer productId);
}
