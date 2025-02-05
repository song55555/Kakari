package com.gbi.kakari.entity.trade;

import java.time.LocalDateTime;

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
@Table(name = "trade")
public class Trade {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tradeId", nullable = false)
	private Integer tradeId;

	@Column(name = "userId", nullable = false, length = 20)
	private String userId;

	@Column(name = "traderId", length = 20)
	private String traderId;

	@Column(name = "productId", nullable = false)
	private Integer productId;

	@Column(name = "tradeDate", nullable = false, columnDefinition = "TIMESTAMP")
	private LocalDateTime tradeDate;

	@Column(name = "tradePrice", nullable = false, precision = 10, scale = 2)
	private Long tradePrice;

	@Column(name = "tradeLocation", nullable = false, length = 255)
	private String tradeLocation;

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private UserInfo userInfo;

	@ManyToOne
	@JoinColumn(name = "traderId", insertable = false, updatable = false)
	private UserInfo traderInfo;

	@ManyToOne
	@JoinColumn(name = "productId", insertable = false, updatable = false)
	private UserCar userCar;

}
