package com.gbi.kakari.entity.rent;

import java.time.LocalDateTime;

import com.gbi.kakari.entity.car.Product;
import com.gbi.kakari.entity.user.UserInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rent")
public class Rent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rentId", nullable = false)
	private Integer rentId;

	@Column(name = "userId", nullable = false, length = 20)
	private String userId;

	@Column(name = "productId", nullable = false)
	private Integer productId;

	@Column(name = "rentLocationId", nullable = false)
	private Integer rentLocationId;

	@Column(name = "rentStartDate", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime rentStartDate;

	@Column(name = "rentEndDate", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime rentEndDate;

	@Column(name = "rentPrice", nullable = false, precision = 10, scale = 2)
	private Long rentPrice;

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private UserInfo userInfo;

	@ManyToOne
	@JoinColumn(name = "productId", insertable = false, updatable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "rentLocationId", insertable = false, updatable = false)
	private RentLocation rentLocation;
}
