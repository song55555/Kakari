package com.gbi.kakari.entity.order;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.gbi.kakari.entity.car.UserCar;
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
@Table(name = "carorder")
public class CarOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderId", nullable = false)
	private Integer orderId;

	@Column(name = "userId", nullable = false, length = 20)
	private String userId;

	// 유저 차량 아이디
	@Column(name = "userCarId", nullable = false)
	private Integer userCarId;

	@Column(name = "orderDate", columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime orderDate;

	@Column(name = "orderPrice", nullable = false, precision = 10, scale = 2)
	private Long orderPrice;

	@Column(name = "orderAddress", nullable = false, length = 255)
	private String orderAddress;

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private UserInfo userInfo;

	@ManyToOne
	@JoinColumn(name = "productId", insertable = false, updatable = false)
	private UserCar userCar;
}
