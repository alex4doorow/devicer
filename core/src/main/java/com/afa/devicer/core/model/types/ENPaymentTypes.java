package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ENPaymentTypes {
		
	PREPAYMENT(1, "предоплата"),
	POSTPAY(2, "постоплата"),
	PAYMENT_COURIER(5, "наличными курьеру"),	
	YANDEX_PAY(6, "банковской картой"),
	APPLE_PAY(7, "Apple Pay"),
	GOOGLE_PAY(8, "Google Pay"),
	CREDIT(9, "в кредит");
	
	private int id;
	private String annotation;

	public static ENPaymentTypes getValueById(long value) {
		if (value == 1) {
			return ENPaymentTypes.PREPAYMENT;
		} else if (value == 2) {
			return ENPaymentTypes.POSTPAY;
		} else if (value == 5) {
			return ENPaymentTypes.PAYMENT_COURIER;
		} else if (value == 6) {
			return ENPaymentTypes.YANDEX_PAY;
		} else if (value == 7) {
			return ENPaymentTypes.APPLE_PAY;
		} else if (value == 8) {
			return ENPaymentTypes.GOOGLE_PAY;
		} else if (value == 9) {
			return ENPaymentTypes.CREDIT;
		} else {
			return ENPaymentTypes.POSTPAY;
		}
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
