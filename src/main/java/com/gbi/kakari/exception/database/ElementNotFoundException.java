package com.gbi.kakari.exception.database;

import com.gbi.kakari.exception.ReasonableException;

public class ElementNotFoundException extends ReasonableException {
	public ElementNotFoundException(String reason) {
		super("DB에 해당 요소가 존재하지 않습니다.", reason);
	}
}
