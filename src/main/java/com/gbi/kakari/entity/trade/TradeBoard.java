package com.gbi.kakari.entity.trade;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.gbi.kakari.entity.user.UserInfo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@DynamicInsert
@Table(name = "tradeboard")
public class TradeBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tradePostId", nullable = false)
	private Integer tradePostId;

	@Column(name = "userId", nullable = false, length = 20)
	private String userId;

	@Column(name = "usedTitle", nullable = false, length = 100)
	private String usedTitle;

	@Column(name = "usedContent", nullable = false, columnDefinition = "TEXT")
	private String usedContent;

	@Column(name = "usedCreatedDate", nullable = false, columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime usedCreatedDate;

	@Column(name = "tradeBoardStatus") // 게시글 상태 컬럼 추가
	@ColumnDefault("0") // 기본값 0 (활성 상태)
	private Integer tradeBoardStatus; // 0: 활성, 1: 판매 완료, 2: 삭제됨 (필요에 따라 확장)

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private UserInfo userInfo;

	@OneToOne // TradeBoard 와 Trade 를 일대일 관계로 매핑
	@JoinColumn(name = "tradeId") // Trade 테이블의 tradeId 를 외래키로 사용
	private Trade trade;
}