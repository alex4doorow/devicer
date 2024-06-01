package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum ENOrderTypes {
		
	UNKNOWN(0, "неопределен"), 	
	ORDER(1, "заказ"),
	BILL(2, "счет на оплату"),
	KP(3, "коммерческое предложение"),
	CONSULTATION(4, "консультация"),	
	CHANGE(11, "замена"),
	REFUND(12, "возврат"),
	GIFT(13, "подарок"),
	TEST_DIVE(14, "тест-драйв"),
	REPAIR(15, "ремонт");

	private int id;
	private String annotation;

	public static ENOrderTypes getValueById(Long value) {
		if (value == 1) {
			return ENOrderTypes.ORDER;
		} else if (value == 2) {
			return ENOrderTypes.BILL;
		} else if (value == 3) {
			return ENOrderTypes.KP;
		} else if (value == 4) {
			return ENOrderTypes.CONSULTATION;
		} else if (value == 11) {
			return ENOrderTypes.CHANGE;
		} else if (value == 12) {
			return ENOrderTypes.REFUND;
		} else if (value == 13) {
			return ENOrderTypes.GIFT;
		} else if (value == 14) {
			return ENOrderTypes.TEST_DIVE;
		} else if (value == 15) {
			return ENOrderTypes.REPAIR;
		} else  {
			return ENOrderTypes.UNKNOWN;
		}
	}
	
	/**
	 * "1,2,3,4" -> Set<ENOrderTypes>
	 * @return
	 */
	public static Set<ENOrderTypes> getStatusesByArray(String strStatuses) {
		Set<ENOrderTypes> statuses = new HashSet<ENOrderTypes>();
		if (StringUtils.isEmpty(strStatuses)) {
			return statuses;
		}
		final String spliter = ",";
		String[] arrStatises = strStatuses.split(spliter);		
		for (String arrStatus : arrStatises) {
			ENOrderTypes status = ENOrderTypes.getValueById(Long.valueOf(arrStatus));
			statuses.add(status);
		}
		return statuses;
	}
	
	public static String getArrayByStatuses(Set<ENOrderTypes> statuses) {
		if (statuses == null || statuses.isEmpty()) {
			return "";
		}
		
		final String spliter = ",";
		String result = "";
		for (ENOrderTypes status : statuses) {
			result += String.valueOf(status.getId()) + spliter;			
		}		
		result = result.trim();
		String spliter1 = result.substring(result.length() - 1, result.length()).trim();
		if (spliter1.equals(spliter)) {
			result = result.substring(0,  result.length() - 1);
		}		
		return result.trim();		
	}
	
	public static ENOrderTypes getValueByAnnotation(String value) {
	
		if (value.equals(ENOrderTypes.ORDER.getAnnotation())) {
			return ENOrderTypes.ORDER;
		} else if (value.equals(ENOrderTypes.BILL.getAnnotation())) {
			return ENOrderTypes.BILL;
		} else if (value.equals(ENOrderTypes.KP.getAnnotation())) {
			return ENOrderTypes.KP;
		} else if (value.equals(ENOrderTypes.CONSULTATION.getAnnotation())) {
			return ENOrderTypes.CONSULTATION;
		} else if (value.equals(ENOrderTypes.CHANGE.getAnnotation())) {
			return ENOrderTypes.CHANGE;
		} else if (value.equals(ENOrderTypes.REFUND.getAnnotation())) {
			return ENOrderTypes.REFUND;
		} else if (value.equals(ENOrderTypes.GIFT.getAnnotation())) {
			return ENOrderTypes.GIFT;
		} else if (value.equals(ENOrderTypes.TEST_DIVE.getAnnotation())) {
			return ENOrderTypes.TEST_DIVE;
		} else if (value.equals(ENOrderTypes.REPAIR.getAnnotation())) {
			return ENOrderTypes.REPAIR;
		}  else {
			return ENOrderTypes.UNKNOWN;
		}		
	}
}
