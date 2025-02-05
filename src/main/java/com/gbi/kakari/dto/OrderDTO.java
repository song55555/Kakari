package com.gbi.kakari.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
	Integer productId;
	Integer productColorId;
	List<Integer> selectedOptions;
	String orderAddress = "sibal";
}
