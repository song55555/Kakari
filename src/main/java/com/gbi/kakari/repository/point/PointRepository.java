package com.gbi.kakari.repository.point;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.point.Point;

@Repository
public interface PointRepository extends JpaRepository<Point, Integer> {
	Point getPointByUserId(String userId);
}
