package com.gbi.kakari.repository.car.option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.car.option.OptionType;

@Repository
public interface OptionTypeRepository extends JpaRepository<OptionType, Integer> {
}
