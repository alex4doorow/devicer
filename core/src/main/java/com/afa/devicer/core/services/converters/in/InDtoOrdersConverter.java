package com.afa.devicer.core.services.converters.in;

import com.afa.devicer.core.bl.entities.BaseEntity;
import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.rest.dto.DtoOrder;
import com.afa.devicer.core.services.converters.IOConverter;
import org.springframework.stereotype.Component;

@Component
public class InDtoOrdersConverter implements IOConverter<DtoOrder, SEOrder> {

    @Override
    public SEOrder convertTo(DtoOrder dtoOrder) {
        SEOrder order = new SEOrder();
        order.setId(dtoOrder.getId());
        order.setOrderNum(dtoOrder.getOrderNum());
        order.setOrderDate(dtoOrder.getOrderDate());

        order.setRecStatus(BaseEntity.ACTIVE);
        return order;
    }
}
