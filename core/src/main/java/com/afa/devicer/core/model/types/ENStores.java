package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ENStores {
	
	/**
	 * [2] pribormaster.ru
	 */
	PM(1L, "pribormaster.ru", "info@pribormaster.ru", "ПРИБОРМАСТЕР", "pm");
		
	private final long id;
	private final String site;
    private final String annotation;
    private final String email;
    private final String prefix;

	public static ENStores getValueById(long value) {

		for (ENStores type : values()) {
			if (Objects.equals(value, type.getId())) {
				return type;
			}
		}
		return null;
	}
}
