package com.gbi.kakari.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gbi.kakari.entity.user.AddressInfo;

@Repository
public interface AddressInfoRepository extends JpaRepository<AddressInfo, String> {
}
