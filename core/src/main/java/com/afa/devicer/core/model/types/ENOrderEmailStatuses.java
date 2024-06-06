package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ENOrderEmailStatuses {
	
	UNKNOWN(0L, "неопределен"),
	FEEDBACK(1L, "запрос отзыва"),
	TERM_EXPAIRED(2L, "запрос на актуальность");
		
	private final long id;
	private final String annotation;
	
	public static ENOrderEmailStatuses getValueById(long value) {
		for (ENOrderEmailStatuses type : values()) {
			if (Objects.equals(value, type.getId())) {
				return type;
			}
		}
		return null;
	}
}
