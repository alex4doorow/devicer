package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ENAddressTypes {
		
	MAIN(1L, "основной"),
	ADDITIONAL(2L, "дополнительный"),
	UNKNOWN(3L, "неизвестный");

	private final Long id;
    private final String annotation;

	public static ENAddressTypes getValueById(long value) {
		for (ENAddressTypes type : values()) {
			if (Objects.equals(value, type.getId())) {
				return type;
			}
		}
		return null;
	}
}
