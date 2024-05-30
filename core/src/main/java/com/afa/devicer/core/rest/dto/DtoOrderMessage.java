package com.afa.devicer.core.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class DtoOrderMessage implements DtoAnyMessage<DtoOrder> {

    private Long offset;
    private DtoOrder order;
    private DtoState state;

    public DtoOrderMessage() {
        order = new DtoOrder();
        state = new DtoState();
    }

    @Override
    public DtoOrder getBody() {
        return order;
    }
}
