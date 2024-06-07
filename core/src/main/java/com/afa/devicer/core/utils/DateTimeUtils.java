package com.afa.devicer.core.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

// todo unit tests

public class DateTimeUtils {
    public static final String DATE_FORMAT_dd_MM_yyyy = "dd.MM.yyyy";
    public static final String DATE_FORMAT_HH_mm = "HH:mm";
    public static final String DATE_FORMAT_HH_mm_EEE = "dd.MM.yyyy, EEE";
    public static final String DATE_FORMAT_UTC = "dd.MM.yyyy HH:mm:ss";
    public static final String DATE_FORMAT_UTC_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_FORMAT_UTC_2 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_UTC_SSS_Z = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
    public static final String DATE_FORMAT_OZON = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static final String DATE_FORMAT_CDEK = "yyyy-MM-dd'T'HH:mm:ssZ"; // "2022-11-21T09:05:06+0000"
    public static final String DATE_FORMAT_yyyy_MM_dd = "yyyy-MM-dd";
    public static final String DATE_FMT_ISO8601 = "yyyy-MM-dd'T'HH:mm:ssXXX";
    public static final String DATETIME_NS = "yyyy-MM-dd'T'HH:mm";

    public static String formatLocalDateTime(LocalDateTime date, String dateFormatString) {
        if (date == null) {
            return "";
        }
        return date.format(DateTimeFormatter.ofPattern(dateFormatString));
    }

    public static String formatLocalDate(LocalDate localDate, String dateFormatString) {
        return localDate.format(DateTimeFormatter.ofPattern(dateFormatString));
    }

    public static String defaultFormatLocalDate(LocalDate date) {
        return formatLocalDate(date, DATE_FORMAT_dd_MM_yyyy);
    }

    public static OffsetDateTime toOffsetDateTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        ZoneId zoneId = ZoneId.systemDefault();
        return localDateTime.atZone(zoneId).toOffsetDateTime();
    }

    public static LocalDate sysDate() {
        return LocalDate.now();
    }

    public static LocalDateTime sysDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate beforeAnyDayOfDate(LocalDate inputDate, int value) {
        return inputDate.minusDays(value);
    }

    public static LocalDate firstDayOfMonth(LocalDate inputDate) {
        return inputDate.withDayOfMonth(1);
    }

    public static LocalDate lastDayOfMonth(LocalDate inputDate) {
        return inputDate.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate firstDayOfQuarter(LocalDate inputDate) {
        int month = inputDate.getMonthValue();
        Month firstQuarterMonth;

        if (month >= 1 && month <= 3) {
            firstQuarterMonth = Month.JANUARY;
        } else if (month >= 4 && month <= 6) {
            firstQuarterMonth = Month.APRIL;
        } else if (month >= 7 && month <= 9) {
            firstQuarterMonth = Month.JULY;
        } else {
            firstQuarterMonth = Month.OCTOBER;
        }

        return LocalDate.of(inputDate.getYear(), firstQuarterMonth, 1);
    }

    public static LocalDate lastDayOfQuarter(LocalDate inputDate) {
        int month = inputDate.getMonthValue();
        LocalDate lastDayOfQuarter;

        if (month >= 1 && month <= 3) {
            lastDayOfQuarter = LocalDate.of(inputDate.getYear(), 3, 31);
        } else if (month >= 4 && month <= 6) {
            lastDayOfQuarter = LocalDate.of(inputDate.getYear(), 6, 30);
        } else if (month >= 7 && month <= 9) {
            lastDayOfQuarter = LocalDate.of(inputDate.getYear(), 9, 30);
        } else {
            lastDayOfQuarter = LocalDate.of(inputDate.getYear(), 12, 31);
        }

        return lastDayOfQuarter;
    }

    public static LocalDate firstDayOfHalfYear(LocalDate inputDate) {
        Month firstHalfYearMonth;

        if (inputDate.getMonthValue() <= 6) {
            firstHalfYearMonth = Month.JANUARY;
        } else {
            firstHalfYearMonth = Month.JULY;
        }

        return LocalDate.of(inputDate.getYear(), firstHalfYearMonth, 1);
    }

    public static LocalDate lastDayOfHalfYear(LocalDate inputDate) {
        LocalDate lastDayOfHalfYear;

        if (inputDate.getMonthValue() <= 6) {
            lastDayOfHalfYear = LocalDate.of(inputDate.getYear(), 6, 30);
        } else {
            lastDayOfHalfYear = LocalDate.of(inputDate.getYear(), 12, 31);
        }

        return lastDayOfHalfYear;
    }

    public static LocalDate firstDayOfYear(LocalDate inputDate) {
        return LocalDate.of(inputDate.getYear(), 1, 1);
    }

    public static LocalDate lastDayOfYear(LocalDate inputDate) {
        return LocalDate.of(inputDate.getYear(), 12, 31);
    }

    public static LocalDate truncateDate(LocalDate inputDate) {
        LocalDateTime dateTime = inputDate.atStartOfDay();
        return dateTime.toLocalDate();
    }

    public static int monthOfDate(LocalDate inputDate) {
        return inputDate.getMonthValue();
    }

    public static int quarterOfDate(LocalDate inputDate) {
        int month = inputDate.getMonthValue();
        if (month <= 3) {
            return 1;
        } else if (month <= 6) {
            return 2;
        } else if (month <= 9) {
            return 3;
        } else {
            return 4;
        }
    }

    public static int halfYearOfDate(LocalDate inputDate) {
        int month = inputDate.getMonthValue();
        if (month <= 6) {
            return 1;
        } else {
            return 2;
        }
    }

    public static int yearOfDate(LocalDate inputDate) {
        return inputDate.getYear();
    }

    public static LocalDate stringToLocalDate(String inputString, String dateFormatString) {
        if (StringUtils.isEmpty(inputString)) {
            return null;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormatString);
        return LocalDate.parse(inputString, formatter);
    }

    public static LocalDate defaultFormatStringToLocalDate(String inputString) {
        return stringToLocalDate(inputString, DATE_FORMAT_dd_MM_yyyy);
    }


}
