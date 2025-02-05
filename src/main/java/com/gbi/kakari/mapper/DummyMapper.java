package com.gbi.kakari.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DummyMapper {

	void insertProduct(String productName, Double price, String productImage);

	void insertProductColor(Integer productId, String colorName);

	void insertOptionType(String optionName);

	void insertProductOption(Integer productId, Integer optionTypeId);

	void insertUserInfo(String userId, String userEmail, String userName, String userPassword, String userPhoneNum, Integer userRole);

	void insertPoint(Integer points, String userId);

	void insertRentLocation(String rentLocationName, String rentLocationAddress, String rentLocationPhoneNum);
}