package com.afa.devicer.core.services.converters.out;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.model.types.*;
import com.afa.devicer.core.rest.dto.*;
import com.afa.devicer.core.services.converters.IOConverter;
import com.afa.devicer.core.services.converters.IOConverterOfList;
import com.afa.devicer.core.utils.DateTimeUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class OutDtoOrdersConverter implements IOConverter<SEOrder, DtoOrder>, IOConverterOfList<SEOrder, DtoOrder> {


    @Override
    public Collection<DtoOrder> convertTo(Collection<SEOrder> orders) {
        return orders
                .stream()
                .map(this::convertTo)
                .toList();
    }

    @Override
    public DtoOrder convertTo(SEOrder seOrder) {
        final Map<ENOrderAmountTypes, BigDecimal> amounts = new HashMap<>();

        //amounts.put(ENOrderAmountTypes.TOTAL, seOrder.getAmounts().getValue(ENOrderAmountTypes.TOTAL));
//        dtoOrder.getAmounts().put(OrderAmountTypes.TOTAL_WITH_DELIVERY, order.getAmounts().getValue(OrderAmountTypes.TOTAL_WITH_DELIVERY));
//        dtoOrder.getAmounts().put(OrderAmountTypes.BILL, order.getAmounts().getValue(OrderAmountTypes.BILL));
//        dtoOrder.getAmounts().put(OrderAmountTypes.SUPPLIER, order.getAmounts().getValue(OrderAmountTypes.SUPPLIER));
//        dtoOrder.getAmounts().put(OrderAmountTypes.MARGIN, order.getAmounts().getValue(OrderAmountTypes.MARGIN));
//        dtoOrder.getAmounts().put(OrderAmountTypes.POSTPAY, order.getAmounts().getValue(OrderAmountTypes.POSTPAY))

        amounts.put(ENOrderAmountTypes.BILL, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.SUPPLIER, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.MARGIN, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.POSTPAY, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.DELIVERY, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.TOTAL, BigDecimal.ZERO);
        amounts.put(ENOrderAmountTypes.TOTAL_WITH_DELIVERY, BigDecimal.ZERO);

        DtoOrder dtoOrder = DtoOrder.builder()
                .id(seOrder.getId())
                .orderNum(seOrder.getOrderNum())
                .orderDate(seOrder.getOrderDate())
                .customer(DtoCustomer.builder()
                        .type(ENCustomerTypes.CUSTOMER)
                        .person(DtoPerson.builder()
                                .firstName("John")
                                .lastName("Smith")
                                .phoneNumber("(916) 596-90-59")
                                .build())
                        .build())
                .type(ENOrderTypes.ORDER)
                .status(ENOrderStatuses.APPROVED)
                .payment(ENPaymentTypes.POSTPAY)
                .amounts(amounts)
                .productCategory(DtoProductCategory.builder()
                        .id(301L)
                        .name("алкотестеры")
                        .group("для дома")
                        .build())
                .dateAdded(DateTimeUtils.toOffsetDateTime(seOrder.getDateAdded()))
                .dateModified(DateTimeUtils.toOffsetDateTime(seOrder.getDateModified()))
                .build();

        dtoOrder.setDelivery(DtoOrderDelivery.builder()
                .parent(dtoOrder).address(DtoAddress.builder()
                        .address("Moscow, Green street 43, 34")
                        .build())
                .type(ENDeliveryTypes.CDEK_PVZ_TYPICAL)
                .trackCode("")
                .build());

        return dtoOrder;
    }

}
