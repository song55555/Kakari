package com.gbi.kakari.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addressinfo")
public class AddressInfo {
	@Id
	@Column(name = "userId", nullable = false, length = 20)
	private String userId;

	@Column(name = "userPostcode", nullable = false)
	@NotNull
	private Integer userPostcode;

	@Column(name = "userAddress", nullable = false, length = 200)
	@NotNull
	private String userAddress;

	@Column(name = "userDetailAddress", length = 50)
	private String userDetailAddress;

	@ManyToOne
	@JoinColumn(name = "userId", insertable = false, updatable = false)
	private UserInfo userInfo;

}
