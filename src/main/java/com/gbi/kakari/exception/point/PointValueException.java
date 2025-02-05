package com.gbi.kakari.exception.point;

import com.gbi.kakari.exception.ReasonableException;

public class PointValueException extends ReasonableException {
	public PointValueException(String reason) {
		super("포인트 값이 올바르지 않습니다", reason);
	}
}
