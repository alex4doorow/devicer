package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ENDeliveryTypes {
		
	UNKNOWN(0, "Неопределено", "неопределено"),
	CDEK_PVZ_TYPICAL(101, "CDEK", "СДЭК, ПВЗ"),
	CDEK_PVZ_ECONOMY(102, "CDEK", "СДЭК, ЭКОНОМ ПВЗ"),
	CDEK_COURIER(103, "CDEK", "СДЭК, курьер"),
	CDEK_COURIER_ECONOMY(104, "CDEK", "СДЭК, ЭКОНОМ курьер"),
	DELLIN(201, "ТК Деловые Линии", "Деловые Линии"),
	COURIER_MOSCOW_TYPICAL(301, "МОСКВА курьер 1-2 дня", "курьер, 1-2 дня"),
	COURIER_MOSCOW_FAST(302, "МОСКВА курьер, сегодня", "курьер, сегодня"),
	COURIER_MO_TYPICAL(303, "МО курьер, 1-2 дня", "МО, курьер"),
	POST_TYPICAL(401, "ПОЧТА РОССИИ", "ПОЧТА, обычное"),
	POST_I_CLASS(402, "ПОЧТА РОССИИ", "ПОЧТА, 1-й класс"),
	POST_EMS(405, "ПОЧТА EMS", "ПОЧТА, EMS"),
	PICKUP(403, "самовывоз, сегодня", "самовывоз"),	
	YANDEX_MARKET_FBS(501, "YANDEX MARKET", "до склада Яндекс-Маркет"),
	OZON_FBS(701, "OZON", "до склада OZON"),	
	YANDEX_GO(601, "YANDEX GO", "Яндекс-Логистика"),
	OZON_ROCKET_PICKPOINT(801, "OZON Rocket", "OZON Rocket ПВЗ", "PickPoint"),
	OZON_ROCKET_POSTAMAT(802, "OZON Rocket", "OZON Rocket постамат", "Postamat"),
	OZON_ROCKET_COURIER(803, "OZON Rocket", "OZON Rocket курьер", "Courier");

	private final int id;
	private final String category;
	private final String annotation;
	private String code;

	ENDeliveryTypes(int id, String category, String annotation) {
		this.id = id;
		this.category = category;
		this.annotation = annotation;
	}

	public static ENDeliveryTypes getValueById(long value) {
		if (value == 101) {
			return ENDeliveryTypes.CDEK_PVZ_TYPICAL;
		} else if (value == 102) {
			return ENDeliveryTypes.CDEK_PVZ_ECONOMY;
		} else if (value == 103) {
			return ENDeliveryTypes.CDEK_COURIER;
		} else if (value == 104) {
			return ENDeliveryTypes.CDEK_COURIER_ECONOMY;
		} else if (value == 201) {
			return ENDeliveryTypes.DELLIN;
		} else if (value == 301) {
			return ENDeliveryTypes.COURIER_MOSCOW_TYPICAL;
		} else if (value == 302) {
			return ENDeliveryTypes.COURIER_MOSCOW_FAST;
		} else if (value == 303) {
			return ENDeliveryTypes.COURIER_MO_TYPICAL;
		} else if (value == 401) {
			return ENDeliveryTypes.POST_TYPICAL;
		} else if (value == 402) {
			return ENDeliveryTypes.POST_I_CLASS;
		} else if (value == 405) {
			return ENDeliveryTypes.POST_EMS;
		} else if (value == 403) {
			return ENDeliveryTypes.PICKUP;
		} else if (value == 501) {
			return ENDeliveryTypes.YANDEX_MARKET_FBS;
		} else if (value == 701) {
			return ENDeliveryTypes.OZON_FBS;
		} else if (value == 601) {
			return ENDeliveryTypes.YANDEX_GO;
		} else if (value == 801) {
			return ENDeliveryTypes.OZON_ROCKET_PICKPOINT;
		} else if (value == 802) {
			return ENDeliveryTypes.OZON_ROCKET_PICKPOINT;
		} else if (value == 803) {
			return ENDeliveryTypes.OZON_ROCKET_COURIER;
		} else  {
			return ENDeliveryTypes.UNKNOWN;
		}	
	}

	public boolean isCdek() {
		if (this == ENDeliveryTypes.PICKUP || this == ENDeliveryTypes.CDEK_PVZ_TYPICAL || this == ENDeliveryTypes.CDEK_PVZ_ECONOMY || this == ENDeliveryTypes.CDEK_COURIER || this == ENDeliveryTypes.CDEK_COURIER_ECONOMY) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isCdekPvz() {
		if (this == ENDeliveryTypes.CDEK_PVZ_TYPICAL || this == ENDeliveryTypes.CDEK_PVZ_ECONOMY || this == ENDeliveryTypes.PICKUP) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isPost() {
		if (this == ENDeliveryTypes.POST_TYPICAL || this == ENDeliveryTypes.POST_I_CLASS || this == ENDeliveryTypes.POST_EMS) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isCourier() {
		if (this == ENDeliveryTypes.COURIER_MOSCOW_TYPICAL || this == ENDeliveryTypes.COURIER_MOSCOW_FAST || this == ENDeliveryTypes.COURIER_MO_TYPICAL) {
			return true;
		} else {
			return false;
		}
	}
		
	public static ENDeliveryTypes getValueByAnnotation(String value) {
		if (value.equals(ENDeliveryTypes.CDEK_PVZ_TYPICAL.getAnnotation())) {
			return ENDeliveryTypes.CDEK_PVZ_TYPICAL;
		} else if (value.equals(ENDeliveryTypes.CDEK_PVZ_ECONOMY.getAnnotation())) {
			return ENDeliveryTypes.CDEK_PVZ_ECONOMY;
		} else if (value.equals(ENDeliveryTypes.CDEK_COURIER.getAnnotation())) {
			return ENDeliveryTypes.CDEK_COURIER;
		} else if (value.equals(ENDeliveryTypes.CDEK_COURIER_ECONOMY.getAnnotation())) {
			return ENDeliveryTypes.CDEK_COURIER_ECONOMY;
		} else if (value.equals(ENDeliveryTypes.DELLIN.getAnnotation())) {
			return ENDeliveryTypes.DELLIN;
		} else if (value.equals(ENDeliveryTypes.COURIER_MOSCOW_TYPICAL.getAnnotation())) {
			return ENDeliveryTypes.COURIER_MOSCOW_TYPICAL;
		} else if (value.equals(ENDeliveryTypes.COURIER_MOSCOW_FAST.getAnnotation())) {
			return ENDeliveryTypes.COURIER_MOSCOW_FAST;
		} else if (value.equals(ENDeliveryTypes.COURIER_MO_TYPICAL.getAnnotation())) {
			return ENDeliveryTypes.COURIER_MO_TYPICAL;
		} else if (value.equals(ENDeliveryTypes.POST_TYPICAL.getAnnotation())) {
			return ENDeliveryTypes.POST_TYPICAL;
		} else if (value.equals(ENDeliveryTypes.POST_I_CLASS.getAnnotation())) {
			return ENDeliveryTypes.POST_I_CLASS;
		} else if (value.equals(ENDeliveryTypes.POST_EMS.getAnnotation())) {
			return ENDeliveryTypes.POST_EMS;
		} else if (value.equals(ENDeliveryTypes.PICKUP.getAnnotation())) {
			return ENDeliveryTypes.PICKUP;
		} else if (value.equals(ENDeliveryTypes.YANDEX_MARKET_FBS.getAnnotation())) {
			return ENDeliveryTypes.YANDEX_MARKET_FBS;
		} else if (value.equals(ENDeliveryTypes.OZON_FBS.getAnnotation())) {
			return ENDeliveryTypes.OZON_FBS;
		} else if (value.equals(ENDeliveryTypes.YANDEX_GO.getAnnotation())) {
			return ENDeliveryTypes.YANDEX_GO;
		} else if (value.equals(ENDeliveryTypes.OZON_ROCKET_PICKPOINT.getAnnotation())) {
			return ENDeliveryTypes.OZON_ROCKET_PICKPOINT;
		} else if (value.equals(ENDeliveryTypes.OZON_ROCKET_POSTAMAT.getAnnotation())) {
			return ENDeliveryTypes.OZON_ROCKET_POSTAMAT;
		} else if (value.equals(ENDeliveryTypes.OZON_ROCKET_COURIER.getAnnotation())) {
			return ENDeliveryTypes.OZON_ROCKET_COURIER;
		} else {
			return ENDeliveryTypes.UNKNOWN;
		}		
	}	
	
	public static String convertValuesToSplitedString(ENDeliveryTypes... values) {
		
		if (values == null || values.length == 0) {
			return "";
		}
		String result = "";
		for (ENDeliveryTypes value : values) {
			result += String.valueOf(value.getId()) + ",";			
		}
		result = result.substring(0, result.length() - 1).trim();
		return result;		
	}

}
