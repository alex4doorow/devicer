package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ENOrderAdvertTypes {
	
	UNKNOWN(0L, "неопределен"),
	ADVERT(1L, "реклама"),
	CONTEXT(2L, "поиск"),
	YOUTUBE(3L, "youTube"),
	LOYALTY(4L, "сарафанное радио"),
	REPEAT_CALL(5L, "повторное обращение"),
	COLD_CALL(6L, "холодный звонок"),
	TENDER(8L, "тендер"),
	YANDEX_MARKET(9L, "яндекс.маркет"),
	CDEK_MARKET(10L, "сдэк.маркет"),
	OZON(11L, "озон");
		
	private final long id;
	private final String annotation;

	public static ENOrderAdvertTypes getValueById(long value) {
		for (ENOrderAdvertTypes type : values()) {
			if (Objects.equals(value, type.getId())) {
				return type;
			}
		}
		return null;
	}
	
	public static ENOrderAdvertTypes getValueByAnnotation(String value) {
		if (value.equals(ENOrderAdvertTypes.ADVERT.getAnnotation())) {
			return ENOrderAdvertTypes.ADVERT;
		} else if (value.equals(ENOrderAdvertTypes.CDEK_MARKET.getAnnotation())) {
			return ENOrderAdvertTypes.CDEK_MARKET;
		} else if (value.equals(ENOrderAdvertTypes.YANDEX_MARKET.getAnnotation())) {
			return ENOrderAdvertTypes.YANDEX_MARKET;
		} else if (value.equals(ENOrderAdvertTypes.OZON.getAnnotation())) {
			return ENOrderAdvertTypes.OZON;
		} else if (value.equals(ENOrderAdvertTypes.COLD_CALL.getAnnotation())) {
			return ENOrderAdvertTypes.COLD_CALL;
		} else if (value.equals(ENOrderAdvertTypes.CONTEXT.getAnnotation())) {
			return ENOrderAdvertTypes.CONTEXT;
		} else if (value.equals(ENOrderAdvertTypes.LOYALTY.getAnnotation())) {
			return ENOrderAdvertTypes.LOYALTY;
		} else if (value.equals(ENOrderAdvertTypes.REPEAT_CALL.getAnnotation())) {
			return ENOrderAdvertTypes.REPEAT_CALL;
		} else if (value.equals(ENOrderAdvertTypes.TENDER.getAnnotation())) {
			return ENOrderAdvertTypes.TENDER;
		} else if (value.equals(ENOrderAdvertTypes.YOUTUBE.getAnnotation())) {
			return ENOrderAdvertTypes.YOUTUBE;
		} else {
			return null;
		}		
	}
	
	public static String convertValuesToSplitedString(ENOrderAdvertTypes... values) {
		
		if (values == null || values.length == 0) {
			return "";
		}
		String result = "";
		for (ENOrderAdvertTypes value : values) {
			result += String.valueOf(value.getId()) + ",";			
		}
		result = result.substring(0, result.length() - 1).trim();
		return result;		
	}
	
	
}
