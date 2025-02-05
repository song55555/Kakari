package com.gbi.kakari.exception;

import lombok.Getter;

public class ReasonableException extends RuntimeException {
	@Getter protected final String reason;

	public ReasonableException(String message, String reason) {
		super(message);
		this.reason = reason;
	}

	public String getReasonableMessage() {
		if (reason == null) {
			return super.getMessage();
		}
		return super.getMessage() + " / Reason: " + reason;
	}
}
