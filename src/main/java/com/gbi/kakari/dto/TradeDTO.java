package com.gbi.kakari.dto;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeDTO implements Serializable { // Serializable implements
	@Serial
	private static final long serialVersionUID = 1L; // serialVersionUID 추가

	private Integer tradeId;
	private String userId;
	private String traderId;
	private Integer productId;
	private Timestamp tradeDate;
	private Long tradePrice;
	private String tradeLocation;
	private String productName; // 상품 이름 추가
	private String productColorName; // 상품 색상 이름 추가
	private String productOptionNames; // 상품 옵션 이름들 추가
}