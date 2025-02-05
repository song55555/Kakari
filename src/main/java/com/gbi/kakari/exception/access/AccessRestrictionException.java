package com.gbi.kakari.exception.access;

import com.gbi.kakari.exception.ReasonableException;

import lombok.Getter;

public class AccessRestrictionException extends ReasonableException {
	@Getter private final String uri;

	public AccessRestrictionException(String uri, String reason) {
		super("엑세스가 제한되었습니다", reason);
		this.uri = uri;
	}

	@Override
	public String getReasonableMessage() {
		return super.getMessage() + " / URI: " + uri;
	}
}
