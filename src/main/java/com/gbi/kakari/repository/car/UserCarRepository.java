package com.gbi.kakari.repository.car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.car.UserCar;

@Repository
public interface UserCarRepository extends JpaRepository<UserCar, Integer> {
	List<UserCar> findByUserId(String userId);
}
