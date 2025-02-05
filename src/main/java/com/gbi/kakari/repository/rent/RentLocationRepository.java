package com.gbi.kakari.repository.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.rent.RentLocation;

@Repository
public interface RentLocationRepository extends JpaRepository<RentLocation, Integer> {
}
