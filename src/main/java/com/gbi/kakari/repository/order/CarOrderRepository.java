package com.gbi.kakari.repository.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.order.CarOrder;

@Repository
public interface CarOrderRepository extends JpaRepository<CarOrder, Integer> {

}
