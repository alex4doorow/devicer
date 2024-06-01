package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum ENReportPeriodTypes {
		
	CURRENT_MONTH(1, "текущий месяц", "месяц"),
	CURRENT_QUARTER(2, "текущий квартал", "квартал"),
	CURRENT_HALF_YEAR(105, "текущее полугодие", "полугодие"),
	CURRENT_YEAR(3, "текущий год", "год"),	
	
	ANY_MONTH(4, "произвольный месяц", "месяц"),
	ANY_QUARTER(102, "произвольный квартал", "квартал"),
	ANY_HALF_YEAR(103, "произвольное полугодие", "полугодие"),
	ANY_YEAR(104, "произвольный год", "год"),	
	ANY_PERIOD(5, "произвольный период", ""),
	
	PRIOR_MONTH(6, "предыдущий месяц", ""),
	LAST_7_DAYS(11, "последние 7 дней", ""),
	LAST_30_DAYS(12, "последние 30 дней", ""),
	LAST_90_DAYS(13, "последние 90 дней", "");
		
	private final int id;
	private final String annotation;
	private final String text;

	public static ENReportPeriodTypes[] getListOrderValues() {
		Set<ENReportPeriodTypes> result = new HashSet<ENReportPeriodTypes>();
		
		result.add(ENReportPeriodTypes.CURRENT_MONTH);
		result.add(ENReportPeriodTypes.CURRENT_QUARTER);
		result.add(ENReportPeriodTypes.CURRENT_HALF_YEAR);
		result.add(ENReportPeriodTypes.CURRENT_YEAR);
		result.add(ENReportPeriodTypes.ANY_MONTH);
		result.add(ENReportPeriodTypes.ANY_PERIOD);
		result.add(ENReportPeriodTypes.PRIOR_MONTH);
		result.add(ENReportPeriodTypes.LAST_7_DAYS);
		result.add(ENReportPeriodTypes.LAST_30_DAYS);
		result.add(ENReportPeriodTypes.LAST_90_DAYS);
		return result.toArray(new ENReportPeriodTypes[result.size()]);
	}
	
	public static ENReportPeriodTypes[] getReportValues() {
		Set<ENReportPeriodTypes> result = new HashSet<ENReportPeriodTypes>();
		
		result.add(ENReportPeriodTypes.ANY_MONTH);
		result.add(ENReportPeriodTypes.ANY_QUARTER);
		result.add(ENReportPeriodTypes.ANY_HALF_YEAR);
		result.add(ENReportPeriodTypes.ANY_YEAR);
		return result.toArray(new ENReportPeriodTypes[result.size()]);
	}
	
	public static ENReportPeriodTypes getValueById(int value) {
		if (value == 1) {
			return ENReportPeriodTypes.CURRENT_MONTH;
		} else if (value == 2) {
			return ENReportPeriodTypes.CURRENT_QUARTER;
		} else if (value == 105) {
			return ENReportPeriodTypes.CURRENT_HALF_YEAR;
		} else if (value == 3) {
			return ENReportPeriodTypes.CURRENT_YEAR;
		} else if (value == 4) {
			return ENReportPeriodTypes.ANY_MONTH;
		} else if (value == 102) {
			return ENReportPeriodTypes.ANY_QUARTER;
		} else if (value == 103) {
			return ENReportPeriodTypes.ANY_HALF_YEAR;
		} else if (value == 104) {
			return ENReportPeriodTypes.ANY_YEAR;
		} else if (value == 5) {
			return ENReportPeriodTypes.ANY_PERIOD;
		} else if (value == 6) {
			return ENReportPeriodTypes.PRIOR_MONTH;
		} else if (value == 11) {
			return ENReportPeriodTypes.LAST_7_DAYS;
		} else if (value == 12) {
			return ENReportPeriodTypes.LAST_30_DAYS;
		} else if (value == 13) {
			return ENReportPeriodTypes.LAST_90_DAYS;
		}  else {
			return ENReportPeriodTypes.ANY_PERIOD;
		}	
}
	
	public static ENReportPeriodTypes getValueByCode(String inputValue) {
		if (StringUtils.isEmpty(inputValue)) {
			return ENReportPeriodTypes.ANY_PERIOD;
		} 
		String value = inputValue.trim().toLowerCase();
		if (value.equals("current-month")) {
			return ENReportPeriodTypes.CURRENT_MONTH;
		} else if (value.equals("prior-month")) {
			return ENReportPeriodTypes.PRIOR_MONTH;
		} else if (value.equals("current-quarter")) {
			return ENReportPeriodTypes.CURRENT_QUARTER;
		} else if (value.equals("current-year")) {
			return ENReportPeriodTypes.CURRENT_YEAR;
		} else if (value.equals("any-month")) {
			return ENReportPeriodTypes.ANY_MONTH;
		} else if (value.equals("any-period")) {
			return ENReportPeriodTypes.ANY_PERIOD;
		} else if (value.equals("last-7-days")) {
			return ENReportPeriodTypes.LAST_7_DAYS;
		} else if (value.equals("last-30-days")) {
			return ENReportPeriodTypes.LAST_30_DAYS;
		} else if (value.equals("last-90-days")) {
			return ENReportPeriodTypes.LAST_90_DAYS;
		}  else {
			return ENReportPeriodTypes.ANY_PERIOD;
		}
	}

}
