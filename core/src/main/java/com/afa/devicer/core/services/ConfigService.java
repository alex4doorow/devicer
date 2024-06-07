package com.afa.devicer.core.services;

import com.afa.devicer.core.bl.UsersQueryBL;
import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.bl.entities.sys.SEConfig;
import com.afa.devicer.core.bl.repositories.sys.ConfigRepository;
import com.afa.devicer.core.model.conditions.OrderConditions;
import com.afa.devicer.core.model.types.ENPeriodTypes;
import com.afa.devicer.core.utils.DateTimeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class ConfigService {

    @Autowired
    private ConfigRepository configRepository;
    @Autowired
    private UsersQueryBL usersQueryBL;

    @Cacheable("seConfigCache")
    public List<SEConfig> getAll() {
        return configRepository.findByRecStatus(BaseEntity.ACTIVE);
    }

    @Cacheable(value = "seConfigCache", key = "#code")
    public SEConfig getValueByCode(String code) {
        log.info("getValueByCode code: {}", code);
        return configRepository.findByCodeAndRecStatus(code, BaseEntity.ACTIVE).orElse(null);
    }

    public OrderConditions loadOrderConditions(Long userId) {

        OrderConditions conditions = new OrderConditions();
        int periodTypeId = usersQueryBL.getShelfIntegerValueByKey(userId, "orderConditionsShelf", "periodType", ENPeriodTypes.ANY_PERIOD.getId());
        conditions.setPeriodType(ENPeriodTypes.getValueById(periodTypeId));

        conditions.setPeriodExist(usersQueryBL.getShelfBooleanValueByKey(userId, "orderConditionsShelf", "periodExist", true));
        conditions.setPeriodMonth(usersQueryBL.getShelfIntegerValueByKey(userId, "orderConditionsShelf", "periodMonth", DateTimeUtils.monthOfDate(LocalDate.now())));
        conditions.setPeriodYear(usersQueryBL.getShelfIntegerValueByKey(userId, "orderConditionsShelf", "periodYear", DateTimeUtils.yearOfDate(LocalDate.now())));

        conditions.setNo(usersQueryBL.getShelfStringValueByKey(userId, "orderConditionsShelf", "num", ""));
        conditions.setTrackCode(usersQueryBL.getDefaultShelfStringValueByKey(userId, "orderConditionsShelf", "trackCode"));
        conditions.setDeliveryAddress(usersQueryBL.getDefaultShelfStringValueByKey(userId, "orderConditionsShelf", "deliveryAddress"));
/*
        int productId = usersQueryBL.getShelfIntegerValueByKey(userId, "orderConditionsShelf", "product", 0);
        if (productId > 0) {
            conditions.setProduct(wikiDao.getProductById(productId));
        } else {
            conditions.setProduct(Product.createEmpty());
        }
*/
        conditions.getCustomerConditions().setPersonPhoneNumber(usersQueryBL.getDefaultShelfStringValueByKey(userId, "orderConditionsShelf", "customerConditions.person.phoneNumber"));
        conditions.getCustomerConditions().setPersonEmail(usersQueryBL.getDefaultShelfStringValueByKey(userId, "orderConditionsShelf", "customerConditions.person.email"));
        conditions.getCustomerConditions().setPersonLastName(usersQueryBL.getDefaultShelfStringValueByKey(userId, "orderConditionsShelf", "customerConditions.person.lastName"));
        conditions.getCustomerConditions().setCompanyInn(usersQueryBL.getDefaultShelfStringValueByKey(userId, "orderConditionsShelf", "customerConditions.company.inn"));
        conditions.getCustomerConditions().setCompanyShortName(usersQueryBL.getDefaultShelfStringValueByKey(userId, "orderConditionsShelf", "customerConditions.company.shortName"));

        LocalDate periodStart = usersQueryBL.getShelfDateValueByKey(userId, "orderConditionsShelf", "period.start", DateTimeUtils.truncateDate(LocalDate.now()));
        LocalDate periodEnd = usersQueryBL.getShelfDateValueByKey(userId, "orderConditionsShelf", "period.end", DateTimeUtils.truncateDate(LocalDate.now()));

        conditions.getPeriod().setStart(periodStart);
        conditions.getPeriod().setEnd(periodEnd);
/*
        Set<Object> dirtyStatuses = TextUtils.getStatusesByArray(usersQueryBL.getShelfStringValueByKey(userId, "orderConditionsShelf", "statuses", ""), OrderStatuses.class);
        conditions.setDirtyStatuses(dirtyStatuses);

        Set<OrderTypes> orderTypes = OrderTypes.getStatusesByArray(usersQueryBL.getShelfStringValueByKey(userId, "orderConditionsShelf", "orderTypes", ""));
        conditions.setTypes(orderTypes);

        Set<Object> dirtyDeliveryTypes = TextUtils.getStatusesByArray(usersQueryBL.getShelfStringValueByKey(userId, "orderConditionsShelf", "deliveryTypes", ""), DeliveryTypes.class);
        conditions.setDirtyDeliveryTypes(dirtyDeliveryTypes);

        Set<Object> dirtyCustomerTypes = TextUtils.getStatusesByArray(usersQueryBL.getShelfStringValueByKey(userId, "orderConditionsShelf", "customerTypes", ""), CustomerTypes.class);
        conditions.setDirtyCustomerTypes(dirtyCustomerTypes);

        Set<Object> dirtyPaymentTypes = TextUtils.getStatusesByArray(usersQueryBL.getShelfStringValueByKey(userId, "orderConditionsShelf", "paymentTypes", ""), PaymentTypes.class);
        conditions.setDirtyPaymentTypes(dirtyPaymentTypes);

        Set<Object> dirtyAdvertTypes = TextUtils.getStatusesByArray(usersQueryBL.getShelfStringValueByKey(userId, "orderConditionsShelf", "advertTypes", ""), OrderAdvertTypes.class);
        conditions.setDirtyAdvertTypes(dirtyAdvertTypes);
*/

        return conditions;
    }

    public void saveOrderConditions(Long userId, OrderConditions conditions) {

        usersQueryBL.saveFormIntegerValue(userId, "orderConditionsForm", "reportPeriodType", conditions.getPeriodType().getId());

        usersQueryBL.saveFormBooleanValue(userId, "orderConditionsForm", "periodExist", conditions.isPeriodExist());

        usersQueryBL.saveFormIntegerValue(userId, "orderConditionsForm", "reportPeriodMonth", conditions.getPeriodMonth());
        usersQueryBL.saveFormIntegerValue(userId, "orderConditionsForm", "reportPeriodYear", conditions.getPeriodYear());

        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "no", conditions.getNo());
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "trackCode", conditions.getTrackCode());
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "deliveryAddress", conditions.getDeliveryAddress());
//        usersQueryBL.saveFormIntegerValue(userId, "orderConditionsForm", "product", conditions.getProduct().getId());

        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "customerConditions.person.phoneNumber", conditions.getCustomerConditions().getPersonPhoneNumber());
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "customerConditions.person.email", conditions.getCustomerConditions().getPersonEmail());
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "customerConditions.person.lastName", conditions.getCustomerConditions().getPersonLastName());

        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "customerConditions.company.inn", conditions.getCustomerConditions().getCompanyInn());
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "customerConditions.company.shortName", conditions.getCustomerConditions().getCompanyShortName());

        usersQueryBL.saveFormDateValue(userId, "orderConditionsForm", "period.start", conditions.getPeriod().getStart());
        usersQueryBL.saveFormDateValue(userId, "orderConditionsForm", "period.end", conditions.getPeriod().getEnd());

        /*
        String strStatuses = TextUtils.getArrayByStatuses(conditions.getDirtyStatuses(), OrderStatuses.class);
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "statuses", strStatuses);

        String strOrderTypes = ENOrderTypes.getArrayByStatuses(conditions.getTypes());
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "orderTypes", strOrderTypes);

        String strDeliveryTypes = TextUtils.getArrayByStatuses(conditions.getDirtyDeliveryTypes(), DeliveryTypes.class);
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "deliveryTypes", strDeliveryTypes);

        String strCustomerTypes = TextUtils.getArrayByStatuses(conditions.getDirtyCustomerTypes(), CustomerTypes.class);
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "customerTypes", strCustomerTypes);

        String strPaymentTypes = TextUtils.getArrayByStatuses(conditions.getDirtyPaymentTypes(), PaymentTypes.class);
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "paymentTypes", strPaymentTypes);

        String strAdvertTypes = TextUtils.getArrayByStatuses(conditions.getDirtyAdvertTypes(), OrderAdvertTypes.class);
        usersQueryBL.saveShelfStringValue(userId, "orderConditionsForm", "advertTypes", strAdvertTypes);

        */
    }
}
