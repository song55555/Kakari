package com.gbi.kakari.dto;

import lombok.Data;

@Data
public class TradeBoardRequestDTO {
	private String usedTitle;
	private String usedContent;
	private Integer productId;
	private String tradeDate; // 날짜와 시간을 String으로 받음
	private String tradeTime;
	private Long tradePrice;
	private String tradeLocation;
}