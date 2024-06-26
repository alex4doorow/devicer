package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ENPaymentDeliveryTypes {
		
	CUSTOMER(1, "покупатель"),
	SELLER(2, "продавец");

	private final long id;
	private final String annotation;

	public static ENPaymentDeliveryTypes getValueById(int value) {
		for (ENPaymentDeliveryTypes type : values()) {
			if (Objects.equals(value, type.getId())) {
				return type;
			}
		}
		return null;
	}
}
