package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ENPaymentDeliveryTypes {
		
	CUSTOMER(1, "покупатель"),
	SELLER(2, "продавец");

	private final int id;
	private final String annotation;

	public static ENPaymentDeliveryTypes getValueById(int value) {
		if (value == 1) {
			return ENPaymentDeliveryTypes.CUSTOMER;
		} else if (value == 2) { 
			return ENPaymentDeliveryTypes.SELLER;
		} else {
			return ENPaymentDeliveryTypes.CUSTOMER;
		}
	}
}
