package com.afa.devicer.core.services.converters.out;

import com.afa.devicer.core.bl.entities.SEOrder;
import com.afa.devicer.core.rest.dto.DtoOrder;
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
                .build();
        // ...
        return dtoOrder;
    }
}
