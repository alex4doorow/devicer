package com.afa.devicer.core.bl;

import com.afa.devicer.core.bl.entities.users.SEUserQuery;
import com.afa.devicer.core.bl.repositories.users.UserQueryRepository;
import com.afa.devicer.core.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
public class UsersQueryBLImpl implements UsersQueryBL {

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Override
    public String getShelfStringValueByKey(Long userId, String shelf, String key, String defaultValue) {

        String value = userQueryRepository.findValueByUserAndShelfAndCode(userId, shelf, key);
        return value == null ? defaultValue : value;
    }

    @Override
    public String getDefaultShelfStringValueByKey(Long userId, String shelf, String key) {
        return getShelfStringValueByKey(userId, shelf, key, "");
    }

    @Override
    public int getShelfIntegerValueByKey(Long userId, String shelf, String key, int defaultValue) {
        String value = getShelfStringValueByKey(userId, shelf, key, null);
        try {
            return StringUtils.isEmpty(value) ? defaultValue : Integer.parseInt(value);
        } catch (Exception e) {
            log.error("error getFormIntegerValueByKey():{},{},{},{}", e, shelf, key, value);
            return defaultValue;
        }
    }

    @Override
    public boolean getShelfBooleanValueByKey(Long userId, String shelf, String key, boolean defaultValue) {
        int value = getShelfIntegerValueByKey(userId, shelf, key, 0);
        return value != 0;
    }

    @Override
    public LocalDate getShelfDateValueByKey(Long userId, String shelf, String key, LocalDate defaultValue) {
        String value = getShelfStringValueByKey(userId, shelf, key, null);
        return StringUtils.isEmpty(value) ? defaultValue : DateTimeUtils.defaultFormatStringToLocalDate(value);
    }

    @Override
    public void saveShelfStringValue(Long userId, String shelf, String key, String value) {

        Optional<SEUserQuery> userQueryRecord = userQueryRepository.findByUserIdAndShelfAndCode(userId, shelf, key);
        if (userQueryRecord.isPresent()) {
            SEUserQuery userQuery = userQueryRecord.get();
            userQuery.setValue(value);
            userQueryRepository.save(userQuery);
        }
    }

    @Override
    public void saveFormDateValue(Long userId, String shelf, String key, LocalDate value) {
        saveShelfStringValue(userId, shelf, key, DateTimeUtils.defaultFormatLocalDate(value));
    }

    @Override
    public void saveFormIntegerValue(Long userId, String shelf, String key, int value) {
        saveShelfStringValue(userId, shelf, key, Integer.toString(value));
    }

    @Override
    public void saveFormBooleanValue(Long userId, String shelf, String key, boolean value) {
        String result = value ? "1" : "0";
        saveShelfStringValue(userId, shelf, key, result);
    }
}
