package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum ENCustomerTypes {
		
	CUSTOMER(1L, "физическое лицо", "Физ. лицо"),
	COMPANY(2L, "юридическое лицо", "Юр. лицо"),
    BUSINESSMAN(3L, "индивидуальный предприниматель", "ИП"),
    FOREIGNER_CUSTOMER(4L, "нерезидент, физическое лицо", "Нерезидент ФЛ"),
    FOREIGNER_COMPANY(5L, "нерезидент, юридическое лицо", "Нерезидент ЮЛ"),
    UNKNOWN(6L, "нечто", "нечто");
	
	private final Long id;
	private final String longName;
	private final String shortName;

	public boolean isPerson() {
		return this == CUSTOMER || this == FOREIGNER_CUSTOMER;
	}

	public boolean isCompany() {
		return this == COMPANY || this == BUSINESSMAN || this == FOREIGNER_COMPANY;
	}

	public static ENCustomerTypes getValueById(long value) {
		for (ENCustomerTypes type : values()) {
			if (Objects.equals(value, type.getId())) {
				return type;
			}
		}
		return null;
	}
	
	public static ENCustomerTypes getValueByAnnotation(String value) {
		if (value.equals(ENCustomerTypes.CUSTOMER.getLongName())) {
			return ENCustomerTypes.CUSTOMER;
		} else if (value.equals(ENCustomerTypes.COMPANY.getLongName())) {
			return ENCustomerTypes.COMPANY;
		} else if (value.equals(ENCustomerTypes.BUSINESSMAN.getLongName())) {
			return ENCustomerTypes.BUSINESSMAN;
		} else if (value.equals(ENCustomerTypes.FOREIGNER_CUSTOMER.getLongName())) {
			return ENCustomerTypes.FOREIGNER_CUSTOMER;
		} else if (value.equals(ENCustomerTypes.FOREIGNER_COMPANY.getLongName())) {
			return ENCustomerTypes.FOREIGNER_COMPANY;
		} else {
			return ENCustomerTypes.UNKNOWN;
		} 
	}	
	
	public static String convertValuesToSplitedString(ENCustomerTypes... values) {
						
		if (values == null || values.length == 0) {
			return "";
		}
		String result = "";
		for (ENCustomerTypes value : values) {
			result += value.getId() + ",";
		}
		result = result.substring(0, result.length() - 1).trim();
		return result;		
	}
}
