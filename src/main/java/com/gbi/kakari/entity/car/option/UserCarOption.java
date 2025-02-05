package com.gbi.kakari.entity.car.option;

import com.gbi.kakari.entity.car.UserCar;

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
@Table(name = "usercaroption")
public class UserCarOption {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_car_option_id", nullable = false)
	private Integer userCarOptionId;

	@Column(name = "user_car_id", nullable = false)
	private Integer userCarId;

	@Column(name = "product_option_id", nullable = false)
	private Integer productOptionId;

	@ManyToOne
	@JoinColumn(name = "user_car_id", insertable = false, updatable = false)
	private UserCar userCar;

	@ManyToOne
	@JoinColumn(name = "product_option_id", insertable = false, updatable = false)
	private ProductOption productOption;
}
