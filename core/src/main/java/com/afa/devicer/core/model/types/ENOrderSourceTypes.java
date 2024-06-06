package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ENOrderSourceTypes {
		
	UNKNOWN(0L, "неопределен"),
	CALL(1L, "звонок"),
	CHAT(2L, "чат"),
	EMAIL(3L, "письмо"),
	LID(4L, "лид"),
	FAST_LID(5L, "быстрый заказ"),
	CALL_BACK(6L, "обратный звонок"),
	TENDER(8L, "тендер");
	
	private final long id;
    private final String annotation;

	public static ENOrderSourceTypes getValueById(long value) {
		for (ENOrderSourceTypes type : values()) {
			if (Objects.equals(value, type.getId())) {
				return type;
			}
		}
		return null;
	}
}
