package com.gbi.kakari.repository.car.option;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.car.option.ProductOption;

@Repository
public interface ProductOptionRepository extends JpaRepository<ProductOption, Integer> {
	List<ProductOption> findByProductId(Integer productId);
}
