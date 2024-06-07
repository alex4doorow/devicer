package com.afa.devicer.core.bl;

import java.time.LocalDate;

public interface UsersQueryBL {

    String getShelfStringValueByKey(Long userId, String shelf, String key, String defaultValue);

    String getDefaultShelfStringValueByKey(Long userId, String shelf, String key);

    int getShelfIntegerValueByKey(Long userId, String shelf, String key, int defaultValue);

    boolean getShelfBooleanValueByKey(Long userId, String shelf, String key, boolean defaultValue);

    LocalDate getShelfDateValueByKey(Long userId, String shelf, String key, LocalDate defaultValue);

    void saveShelfStringValue(Long userId, String shelf, String key, String value);

    void saveFormDateValue(Long userId, String shelf, String key, LocalDate value);

    void saveFormIntegerValue(Long userId, String shelf, String key, int value);

    void saveFormBooleanValue(Long userId, String shelf, String key, boolean value);

}
