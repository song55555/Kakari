package com.gbi.kakari.dto;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

import com.gbi.kakari.entity.trade.TradeBoard;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TradeBoardDetailDTO implements Serializable { // Serializable implements
	@Serial
	private static final long serialVersionUID = 1L; // serialVersionUID 추가

	private Integer tradePostId;
	private String userId;
	private String userName;
	private String usedTitle;
	private String usedContent;
	private Timestamp usedCreatedDate;
	private Timestamp usedModifiedDate; // TradeBoard 엔티티에 수정일자가 없으므로 일단 제외
	private Integer tradeBoardStatus; // 게시글 상태 추가
	private TradeDTO tradeDTO;

	public TradeBoardDetailDTO(TradeBoard tradeBoard, TradeDTO tradeDTO, String userName) {
		this.tradePostId = tradeBoard.getTradePostId();
		this.userId = tradeBoard.getUserId();
		this.userName = userName;
		this.usedTitle = tradeBoard.getUsedTitle();
		this.usedContent = tradeBoard.getUsedContent();
		this.usedCreatedDate = Timestamp.valueOf(tradeBoard.getUsedCreatedDate());
		this.tradeBoardStatus = tradeBoard.getTradeBoardStatus();
		this.tradeDTO = tradeDTO;
	}
}