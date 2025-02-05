package com.gbi.kakari.type.point;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PurchaseType {
	INVALID(-1),
	DEFAULT(0),
	ORDER(1),
	RENT(2),
	TRADE(3); // TRADE 추가
	private final int value;

	public static PurchaseType valueOf(int value) {
		for (PurchaseType role : values()) {
			if (role.getValue() == value) {
				return role;
			}
		}
		return PurchaseType.INVALID;
	}
}