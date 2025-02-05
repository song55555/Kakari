package com.gbi.kakari.type.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
	INVALID(-1), USER(0), ADMIN(Integer.MAX_VALUE);

	private final Integer value;

	public static Role valueOf(Integer value) {
		for (Role role : values()) {
			if (role.getValue() == value.intValue()) {
				return role;
			}
		}
		return Role.INVALID;
	}
}
