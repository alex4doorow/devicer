package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ENPaymentTypes {
		
	PREPAYMENT(1L, "предоплата"),
	POSTPAY(2L, "постоплата"),
	PAYMENT_COURIER(5L, "наличными курьеру"),
	YANDEX_PAY(6L, "банковской картой"),
	APPLE_PAY(7L, "Apple Pay"),
	GOOGLE_PAY(8L, "Google Pay"),
	CREDIT(9L, "в кредит");
	
	private final Long id;
	private final String annotation;

	public static ENPaymentTypes getValueById(long value) {

		for (ENPaymentTypes type : values()) {
			if (Objects.equals(value, type.getId())) {
				return type;
			}
		}
		return null;
	}
		
	public static ENPaymentTypes getValueByAnnotation(String value) {
		if (value.equals(ENPaymentTypes.PREPAYMENT.getAnnotation())) {
			return ENPaymentTypes.PREPAYMENT;
		} else if (value.equals(ENPaymentTypes.POSTPAY.getAnnotation())) {
			return ENPaymentTypes.POSTPAY;
		} else if (value.equals(ENPaymentTypes.PAYMENT_COURIER.getAnnotation())) {
			return ENPaymentTypes.PAYMENT_COURIER;
		} else if (value.equals(ENPaymentTypes.YANDEX_PAY.getAnnotation())) {
			return ENPaymentTypes.YANDEX_PAY;
		} else if (value.equals(ENPaymentTypes.APPLE_PAY.getAnnotation())) {
			return ENPaymentTypes.APPLE_PAY;
		} else if (value.equals(ENPaymentTypes.GOOGLE_PAY.getAnnotation())) {
			return ENPaymentTypes.GOOGLE_PAY;
		} else if (value.equals(ENPaymentTypes.CREDIT.getAnnotation())) {
			return ENPaymentTypes.CREDIT;
		} else {
			return null;
		}		
	}	
	
	public static String convertValuesToSplitedString(ENPaymentTypes... values) {
		
		if (values == null || values.length == 0) {
			return "";
		}
		String result = "";
		for (ENPaymentTypes value : values) {
			result += String.valueOf(value.getId()) + ",";			
		}
		result = result.substring(0, result.length() - 1).trim();
		return result;		
	}
}
