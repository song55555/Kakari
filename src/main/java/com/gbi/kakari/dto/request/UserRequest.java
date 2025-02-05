package com.gbi.kakari.dto.request;

import com.gbi.kakari.dto.UserDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserRequest extends AuthRequest {
	private String user_name;
	private String user_email;
	private String user_phone;

	public UserDTO toDTO() {
		UserDTO dto = new UserDTO();
		dto.setUserId(getUser_id());
		dto.setUserPassword(getUser_password());
		dto.setUserName(user_name);
		dto.setUserEmail(user_email);
		dto.setUserPhone(user_phone);
		return dto;
	}
}
