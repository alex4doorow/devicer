package com.afa.devicer.core.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static String formatLocalDateTime(LocalDateTime date, String dateFormatString) {
        if (date == null) {
            return "";
        }
        return date.format(DateTimeFormatter.ofPattern(dateFormatString));
    }
}
