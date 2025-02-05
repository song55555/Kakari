package com.gbi.kakari.repository.car.option;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.car.option.UserCarOption;

@Repository
public interface UserCarOptionRepository extends JpaRepository<UserCarOption, Integer> {
	List<UserCarOption> findByUserCarId(Integer userCarId);
}
