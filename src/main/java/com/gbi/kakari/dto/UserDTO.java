package com.gbi.kakari.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends AuthDTO {
	private String userName;
	private String userEmail;
	private String userPhone;
	private String userRole;
}
