package com.afa.devicer.core.services.converters.out;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.model.types.ENCustomerTypes;
import com.afa.devicer.core.model.types.ENOrderStatuses;
import com.afa.devicer.core.model.types.ENOrderTypes;
import com.afa.devicer.core.rest.dto.DtoCustomer;
import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.core.rest.dto.DtoOrderDelivery;
import com.afa.devicer.core.rest.dto.DtoProductCategory;
import com.afa.devicer.core.services.converters.IOConverter;
import com.afa.devicer.core.services.converters.IOConverterOfList;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        DtoOrder dtoOrder = DtoOrder.builder()
                .id(seOrder.getId())
                .orderNum(seOrder.getOrderNum())
                .orderDate(seOrder.getOrderDate())
                .customer(new DtoCustomer(ENCustomerTypes.CUSTOMER))
                .type(ENOrderTypes.ORDER)
                .status(ENOrderStatuses.APPROVED)
                .productCategory(new DtoProductCategory())
                .build();

        dtoOrder.setDelivery(new DtoOrderDelivery(dtoOrder));

        // ...
        return dtoOrder;
    }
}
