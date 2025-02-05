package com.gbi.kakari.dto;

import com.gbi.kakari.type.point.PurchaseType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PurchaseDTO extends PointDTO {
	private PurchaseType purchaseType;
}
