package com.gbi.kakari.entity.faq;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
@Table(name = "faq")
public class FAQ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faqId", nullable = false)
	private Integer faqId;

	@Column(name = "userId", nullable = false, length = 20)
	private String userId;

	@Column(name = "faqTitle", nullable = false, length = 100)
	private String faqTitle;

	@Column(name = "faqContent", nullable = false, columnDefinition = "TEXT")
	private String faqContent;

	@Column(name = "faqCreatedDate", nullable = false, columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime faqCreatedDate;

	@Column(name = "faqModifiedDate", nullable = false, columnDefinition = "TIMESTAMP")
	@UpdateTimestamp
	private LocalDateTime faqModifiedDate;

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private UserInfo userInfo;
}
