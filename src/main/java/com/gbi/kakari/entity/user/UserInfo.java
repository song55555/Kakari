package com.gbi.kakari.entity.user;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.gbi.kakari.type.user.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "userinfo")
@DynamicInsert
public class UserInfo {
	@Id
	@Column(name = "userId", unique = true, nullable = false, length = 20)
	@Size(min = 8, max = 20)
	private String userId;

	@Column(name = "userName", nullable = false, length = 20)
	@Size(min = 2, max = 20)
	private String userName;

	@Column(name = "userPassword", nullable = false, length = 16)
	@Size(min = 8, max = 16)
	private String userPassword;

	@Column(name = "userEmail", nullable = false, length = 50)
	@Size(max = 50)
	private String userEmail;

	@Column(name = "userPhoneNum", nullable = false, length = 20)
	@Size(max = 20)
	private String userPhoneNum;

	@Column(name = "userCreatedDate", columnDefinition = "TIMESTAMP")
	@CreationTimestamp
	private LocalDateTime userCreatedDate;

	@Column(name = "userRole")
	@ColumnDefault("0")
	private Integer userRole;

	@Builder
	public UserInfo(String userId, String userName, String userPassword, String userEmail, String userPhoneNum, Role userRole) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userPhoneNum = userPhoneNum;
		this.userRole = userRole.getValue();
	}
}
