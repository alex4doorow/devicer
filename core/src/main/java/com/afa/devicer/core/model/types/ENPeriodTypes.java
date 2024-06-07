package com.afa.devicer.core.model.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum ENPeriodTypes {

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

    public static ENPeriodTypes[] getListOrderValues() {
        Set<ENPeriodTypes> result = new HashSet<ENPeriodTypes>();

        result.add(ENPeriodTypes.CURRENT_MONTH);
        result.add(ENPeriodTypes.CURRENT_QUARTER);
        result.add(ENPeriodTypes.CURRENT_HALF_YEAR);
        result.add(ENPeriodTypes.CURRENT_YEAR);
        result.add(ENPeriodTypes.ANY_MONTH);
        result.add(ENPeriodTypes.ANY_PERIOD);
        result.add(ENPeriodTypes.PRIOR_MONTH);
        result.add(ENPeriodTypes.LAST_7_DAYS);
        result.add(ENPeriodTypes.LAST_30_DAYS);
        result.add(ENPeriodTypes.LAST_90_DAYS);
        return result.toArray(new ENPeriodTypes[result.size()]);
    }

    public static ENPeriodTypes[] getReportValues() {
        Set<ENPeriodTypes> result = new HashSet<ENPeriodTypes>();

        result.add(ENPeriodTypes.ANY_MONTH);
        result.add(ENPeriodTypes.ANY_QUARTER);
        result.add(ENPeriodTypes.ANY_HALF_YEAR);
        result.add(ENPeriodTypes.ANY_YEAR);
        return result.toArray(new ENPeriodTypes[result.size()]);
    }

    public static ENPeriodTypes getValueById(int value) {
        for (ENPeriodTypes type : values()) {
            if (Objects.equals(value, type.getId())) {
                return type;
            }
        }
		return null;
    }

    public static ENPeriodTypes getValueByCode(String inputValue) {
        if (StringUtils.isEmpty(inputValue)) {
            return ENPeriodTypes.ANY_PERIOD;
        }
        String value = inputValue.trim().toLowerCase();
        if (value.equals("current-month")) {
            return ENPeriodTypes.CURRENT_MONTH;
        } else if (value.equals("prior-month")) {
            return ENPeriodTypes.PRIOR_MONTH;
        } else if (value.equals("current-quarter")) {
            return ENPeriodTypes.CURRENT_QUARTER;
        } else if (value.equals("current-year")) {
            return ENPeriodTypes.CURRENT_YEAR;
        } else if (value.equals("any-month")) {
            return ENPeriodTypes.ANY_MONTH;
        } else if (value.equals("any-period")) {
            return ENPeriodTypes.ANY_PERIOD;
        } else if (value.equals("last-7-days")) {
            return ENPeriodTypes.LAST_7_DAYS;
        } else if (value.equals("last-30-days")) {
            return ENPeriodTypes.LAST_30_DAYS;
        } else if (value.equals("last-90-days")) {
            return ENPeriodTypes.LAST_90_DAYS;
        } else {
            return ENPeriodTypes.ANY_PERIOD;
        }
    }
}
