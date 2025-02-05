package com.gbi.kakari.repository.point;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.point.PointHistory;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistory, Integer> {
	List<PointHistory> findAllByUserId(String userId);
}
