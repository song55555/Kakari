package com.gbi.kakari.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class RentDTO implements Serializable { // Implement Serializable interface
	@Serial // Explicitly declare serialVersionUID
	private static final long serialVersionUID = 1L; // Recommended for Serializable classes

	private Integer rentLocationId;
	private String rentStartDate;
	private String rentStartTime;
	private String rentEndDate;
	private String rentEndTime;
	private Integer productId;

	public LocalDateTime getRentStartDateTime() {
		return LocalDateTime.parse(rentStartDate + "T" + rentStartTime);
	}

	public LocalDateTime getRentEndDateTime() {
		return LocalDateTime.parse(rentEndDate + "T" + rentEndTime);
	}

	public Long getDurationHours() {
		return Duration.between(getRentStartDateTime(), getRentEndDateTime()).toHours();
	}

	public boolean IsNullRentDTO() {
		return rentLocationId == null || rentStartDate == null || rentStartTime == null || rentEndDate == null || rentEndTime == null
			|| productId == null;
	}
}
