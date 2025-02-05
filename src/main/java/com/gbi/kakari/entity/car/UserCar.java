package com.gbi.kakari.entity.car;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "usercar")
public class UserCar {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_car_id", nullable = false)
	private Integer userCarId;

	@Column(name = "user_id", nullable = false, length = 20)
	private String userId;

	@Column(name = "product_id", nullable = false)
	private Integer productId;

	@Column(name = "product_color_id", nullable = false)
	private Integer productColorId;

	@Column(name = "car_reg_date", nullable = false, columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime carRegDate;

	@ManyToOne
	@JoinColumn(name = "user_id", insertable = false, updatable = false)
	private UserInfo userInfo;

	@ManyToOne
	@JoinColumn(name = "product_id", insertable = false, updatable = false)
	private Product product;

	@ManyToOne
	@JoinColumn(name = "product_color_id", insertable = false, updatable = false)
	private ProductColor productColor;
}
