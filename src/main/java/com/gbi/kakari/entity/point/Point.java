package com.gbi.kakari.entity.point;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
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
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "point")
public class Point {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pointId", nullable = false)
	private Integer pointId;

	@Column(name = "userId", nullable = false, length = 20)
	private String userId;

	@Setter
	@Column(name = "points", nullable = false)
	private Long points;

	@Column(name = "pointChangeDate", columnDefinition = "TIMESTAMP")
	@ColumnDefault("CURRENT_TIMESTAMP")
	@UpdateTimestamp
	private LocalDateTime pointChangeDate;

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private UserInfo userInfo;
}
