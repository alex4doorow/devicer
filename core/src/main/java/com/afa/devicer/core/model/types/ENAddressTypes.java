package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ENAddressTypes {
		
	MAIN(1, "основной"),
	ADDITIONAL(2, "дополнительный"),
	UNKNOWN(3, "неизвестный");

	private final int id;
    private final String annotation;

	public static ENAddressTypes getValueById(long value) {
		if (value == 1) {
			return ENAddressTypes.MAIN;
		} else if (value == 2) {
			return ENAddressTypes.ADDITIONAL;
		} else {
			return ENAddressTypes.UNKNOWN;
		}
	}
}
