package com.afa.devicer.core.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DtoOrderMessage implements DtoAnyMessage<DtoOrder> {

    private Long offset;
    private DtoOrder order;
    private DtoState state;

    @Override
    public DtoOrder getBody() {
        return order;
    }
}
