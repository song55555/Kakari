package com.gbi.kakari.entity.faq;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "faqcomm")
public class FAQComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faqCommentId", nullable = false)
	private Integer faqCommentId;

	@Column(name = "faqId", nullable = false)
	private Integer faqId;

	@Column(name = "usedContent", nullable = false, columnDefinition = "TEXT")
	private String usedContent;

	@Column(name = "usedCreatedDate", nullable = false, columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime usedCreatedDate;

	@ManyToOne
	@JoinColumn(name = "faqId", insertable = false, updatable = false)
	private FAQ faq;
}
