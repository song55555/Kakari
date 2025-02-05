package com.gbi.kakari.repository.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.rent.Rent;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {
}
