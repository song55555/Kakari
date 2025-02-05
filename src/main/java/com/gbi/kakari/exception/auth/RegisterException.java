package com.gbi.kakari.exception.auth;

import com.gbi.kakari.exception.ReasonableException;

public class RegisterException extends ReasonableException {
	public RegisterException(String reason) {
		super("회원가입에 실패했습니다", reason);
	}
}
