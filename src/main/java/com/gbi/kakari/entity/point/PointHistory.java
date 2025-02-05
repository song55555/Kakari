package com.gbi.kakari.entity.point;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.gbi.kakari.entity.order.CarOrder;
import com.gbi.kakari.entity.rent.Rent;
import com.gbi.kakari.entity.trade.Trade;
import com.gbi.kakari.entity.user.UserInfo;
import com.gbi.kakari.type.point.PurchaseType;

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
@Table(name = "pointhistory")
public class PointHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pointId", nullable = false)
	private Integer pointHistoryId;

	@Column(name = "userId", nullable = false, length = 20)
	private String userId;

	@Column(name = "points", nullable = false)
	private Long remainPoints;

	@Column(name = "pointChange", nullable = false)
	private Long pointChange;

	@Column(name = "pointChangeDate", columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime pointChangeDate;

	@Column(name = "orderId")
	private Integer orderId;

	@Column(name = "rentId")
	private Integer rentId;

	@Column(name = "tradeId")
	private Integer tradeId;

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private UserInfo userInfo;

	@ManyToOne
	@JoinColumn(name = "orderId", insertable = false, updatable = false)
	private CarOrder carOrder;

	@ManyToOne
	@JoinColumn(name = "rentId", insertable = false, updatable = false)
	private Rent rent;

	@ManyToOne
	@JoinColumn(name = "tradeId", insertable = false, updatable = false)
	private Trade trade;

	public void SetPurchaseType(PurchaseType purchaseType, Integer id) {
		switch (purchaseType) {
			case ORDER:
				this.orderId = id;
				break;
			case RENT:
				this.rentId = id;
				break;
			case TRADE:
				this.tradeId = id;
				break;
		}
	}
}
