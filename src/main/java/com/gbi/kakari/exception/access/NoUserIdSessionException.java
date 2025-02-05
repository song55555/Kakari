package com.gbi.kakari.exception.access;

import com.gbi.kakari.exception.ReasonableException;

public class NoUserIdSessionException extends ReasonableException {
	public NoUserIdSessionException(String reason) {
		super("로그인 세션이 만료되었습니다", reason);
	}
}
