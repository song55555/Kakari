package com.gbi.kakari.dto;

import lombok.Data;

@Data
public class RentLocationDTO {
	private String rentLocationName;
	private String rentLocationAddress;
	private String locationPhoneNum;

	public String GetFullLocation() {
		return String.format("%s (주소: %s / 전화번호: %s)", rentLocationName, rentLocationAddress, locationPhoneNum);
	}
}
