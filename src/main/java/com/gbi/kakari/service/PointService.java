package com.gbi.kakari.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gbi.kakari.entity.point.Point;
import com.gbi.kakari.entity.point.PointHistory;
import com.gbi.kakari.exception.point.PointValueException;
import com.gbi.kakari.repository.point.PointHistoryRepository;
import com.gbi.kakari.repository.point.PointRepository;
import com.gbi.kakari.type.point.PurchaseType;

@Service
public class PointService {
	private final PointRepository pointRepository;
	private final PointHistoryRepository pointHistoryRepository;

	@Autowired
	public PointService(PointRepository pointRepository, PointHistoryRepository pointHistoryRepository) {
		this.pointRepository = pointRepository;
		this.pointHistoryRepository = pointHistoryRepository;
	}

	public void usePoint(String userId, PurchaseType purchaseType, Integer purchaseId, Long point) {
		if (point < 0) {
			throw new PointValueException("포인트는 0 이상이어야 합니다.");
		}

		Point userPoint = pointRepository.getPointByUserId(userId);
		userPoint.setPoints(userPoint.getPoints() - point);
		pointRepository.save(userPoint);

		Long remainPoint = getRemainPoint(userId);

		PointHistory pointHistory = PointHistory.builder()
			.userId(userId)
			.remainPoints(remainPoint)
			.pointChange(point)
			.build();
		pointHistory.SetPurchaseType(purchaseType, purchaseId);
		pointHistoryRepository.save(pointHistory);
	}

	public void chargePoint(String userId, Long point) {
		if (point < 0) {
			throw new PointValueException("포인트는 0 이상이어야 합니다.");
		}

		Point userPoint = pointRepository.getPointByUserId(userId);
		userPoint.setPoints(userPoint.getPoints() + point);
		pointRepository.save(userPoint);

		Long remainPoint = getRemainPoint(userId);

		PointHistory pointHistoryDTO = PointHistory.builder()
			.userId(userId)
			.remainPoints(remainPoint)
			.pointChange(point)
			.build();
		pointHistoryRepository.save(pointHistoryDTO);
	}

	public List<PointHistory> getAllPointHistory(String userId) {
		return pointHistoryRepository.findAllByUserId(userId);
	}

	private Long getRemainPoint(String userId) {
		Point userPoint = pointRepository.getPointByUserId(userId);
		return userPoint.getPoints();
	}
}
