package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ENCustomerTypes {
		
	CUSTOMER(1, "физическое лицо", "Физ. лицо"),
	COMPANY(2, "юридическое лицо", "Юр. лицо"),
    BUSINESSMAN(3, "индивидуальный предприниматель", "ИП"),
    FOREIGNER_CUSTOMER(4, "нерезидент, физическое лицо", "Нерезидент ФЛ"),
    FOREIGNER_COMPANY(5, "нерезидент, юридическое лицо", "Нерезидент ЮЛ"),
    UNKNOWN(6, "нечто", "нечто");
	
	private final int id;
	private final String longName;
	private final String shortName;

	public boolean isPerson() {
		return this == CUSTOMER || this == FOREIGNER_CUSTOMER;
	}

	public boolean isCompany() {
		return this == COMPANY || this == BUSINESSMAN || this == FOREIGNER_COMPANY;
	}

	public static ENCustomerTypes getValueById(int value) {
		if (value == 1) {
			return ENCustomerTypes.CUSTOMER;
		} else if (value == 2) {
			return ENCustomerTypes.COMPANY;
		} else if (value == 3) {
			return ENCustomerTypes.BUSINESSMAN;
		} else if (value == 4) {
			return ENCustomerTypes.FOREIGNER_CUSTOMER;
		} else if (value == 5) {
			return ENCustomerTypes.FOREIGNER_COMPANY;
		} else {
			return ENCustomerTypes.UNKNOWN;
		}
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
