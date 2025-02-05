package com.gbi.kakari.entity.rent;

import java.io.Serial;
import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rentlocation")
public class RentLocation implements Serializable { // Implement Serializable interface
	@Serial // Explicitly declare serialVersionUID
	private static final long serialVersionUID = 1L; // Recommended for Serializable classes

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rentLocationId", nullable = false)
	private Integer rentLocationId;

	@Column(name = "rentLocationName", nullable = false, length = 255)
	private String rentLocationName;

	@Column(name = "rentLocationAddress", nullable = false, length = 511)
	private String rentLocationAddress;

	@Column(name = "rentLocationPhoneNum", nullable = false, length = 20)
	@Size(max = 20)
	private String rentLocationPhoneNum;

	public boolean IsNullRentLocation() {
		return rentLocationName == null || rentLocationAddress == null || rentLocationPhoneNum == null;
	}
}
