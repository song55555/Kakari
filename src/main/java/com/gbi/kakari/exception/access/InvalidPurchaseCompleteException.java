package com.gbi.kakari.exception.access;

public class InvalidPurchaseCompleteException extends InvalidAccessException {
	public InvalidPurchaseCompleteException() {
		super("결제 과정을 거치지 않고 완료 페이지에 접속했습니다"); // 기본 이유 설정
	}
}
