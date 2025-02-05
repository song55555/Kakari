package com.gbi.kakari.exception.access;

import com.gbi.kakari.exception.ReasonableException;

public class InvalidAccessException extends ReasonableException {
	public InvalidAccessException(String reason) {
		super("잘못된 접근입니다.", reason); // 기본 메시지 및 이유 설정
	}
}