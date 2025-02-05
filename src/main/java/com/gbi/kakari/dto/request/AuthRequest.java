package com.gbi.kakari.dto.request;

import com.gbi.kakari.dto.AuthDTO;

import lombok.Data;

@Data
public class AuthRequest {
	private String user_id;
	private String user_password;

	public AuthDTO toDTO() {
		AuthDTO dto = new AuthDTO();
		dto.setUserId(user_id);
		dto.setUserPassword(user_password);
		return dto;
	}
}
