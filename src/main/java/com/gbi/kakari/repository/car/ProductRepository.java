package com.gbi.kakari.repository.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.car.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
