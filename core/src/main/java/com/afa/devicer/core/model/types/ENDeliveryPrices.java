package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public enum ENDeliveryPrices {
	
	UNKNOWN(0, ENDeliveryTypes.UNKNOWN, "", BigDecimal.ZERO),
	COURIER_MOSCOW_TYPICAL(1, ENDeliveryTypes.COURIER_MOSCOW_TYPICAL, "Москва, обычный", BigDecimal.valueOf(300)),
	COURIER_MOSCOW_FAST(2, ENDeliveryTypes.COURIER_MOSCOW_FAST, "Москва, срочный", BigDecimal.valueOf(500)),
	
	COURIER_MO_TYPICAL_MKAD_15_KM(3, ENDeliveryTypes.COURIER_MO_TYPICAL, "область, <= 15 км", BigDecimal.valueOf(500)),
	COURIER_MO_TYPICAL_MKAD_16_35_KM(4, ENDeliveryTypes.COURIER_MO_TYPICAL, "область, 16-35 км", BigDecimal.valueOf(800)),
	COURIER_MO_TYPICAL_MKAD_36_60_KM(5, ENDeliveryTypes.COURIER_MO_TYPICAL, "область, 36-60 км", BigDecimal.valueOf(1300)),
	COURIER_MO_TYPICAL_MKAD_61_100_KM(6, ENDeliveryTypes.COURIER_MO_TYPICAL, "область, 61-100 км", BigDecimal.valueOf(1900));
	
	private int id;
	private ENDeliveryTypes deliveryType;
	private String annotation;	
	private BigDecimal price;
	
	public static ENDeliveryPrices getValueById(int value) {
		if (value == 1) {
			return ENDeliveryPrices.COURIER_MOSCOW_TYPICAL;
		} else if (value == 2) {
			return ENDeliveryPrices.COURIER_MOSCOW_FAST;
		} else if (value == 3) {
			return ENDeliveryPrices.COURIER_MO_TYPICAL_MKAD_15_KM;
		} else if (value == 4) {
			return ENDeliveryPrices.COURIER_MO_TYPICAL_MKAD_16_35_KM;
		} else if (value == 5) {
			return ENDeliveryPrices.COURIER_MO_TYPICAL_MKAD_36_60_KM;
		} else if (value == 6) {
			return ENDeliveryPrices.COURIER_MO_TYPICAL_MKAD_61_100_KM;
		} else {
			return ENDeliveryPrices.UNKNOWN;
		}		
	}
	
	public static List<ENDeliveryPrices> getValuesByDeliveryType(ENDeliveryTypes deliveryType) {
		List<ENDeliveryPrices> deliveryPrices = new ArrayList<ENDeliveryPrices>();
		if (deliveryType == ENDeliveryTypes.COURIER_MO_TYPICAL) {
			deliveryPrices.add(ENDeliveryPrices.COURIER_MO_TYPICAL_MKAD_15_KM);
			deliveryPrices.add(ENDeliveryPrices.COURIER_MO_TYPICAL_MKAD_16_35_KM);
			deliveryPrices.add(ENDeliveryPrices.COURIER_MO_TYPICAL_MKAD_36_60_KM);
			deliveryPrices.add(ENDeliveryPrices.COURIER_MO_TYPICAL_MKAD_61_100_KM);
		} else if (deliveryType == ENDeliveryTypes.COURIER_MOSCOW_TYPICAL) {
			deliveryPrices.add(ENDeliveryPrices.COURIER_MOSCOW_TYPICAL);
		} else if (deliveryType == ENDeliveryTypes.COURIER_MOSCOW_FAST) {
			deliveryPrices.add(ENDeliveryPrices.COURIER_MOSCOW_FAST);
		}
		return deliveryPrices;
		
	}
}
