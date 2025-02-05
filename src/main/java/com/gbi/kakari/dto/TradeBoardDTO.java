package com.gbi.kakari.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TradeBoardDTO {
	private Integer tradePostId;
	private String userId;
	private String userName; // 유저 이름 추가
	private String usedTitle;
	private Timestamp usedCreatedDate;
	private Integer tradeId; // Trade ID 추가
	private Long tradePrice; // 거래 가격 추가
	private String productName; // 상품 이름 추가
	private Integer tradeBoardStatus; // 게시글 상태 추가
}