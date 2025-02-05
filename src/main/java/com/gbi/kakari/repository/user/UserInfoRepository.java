package com.gbi.kakari.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.user.UserInfo;

import jakarta.validation.constraints.Size;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, String> {
	boolean existsByUserEmail(@Size(max = 50) String userEmail);
}
